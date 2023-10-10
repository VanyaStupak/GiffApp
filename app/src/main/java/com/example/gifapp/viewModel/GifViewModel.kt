package com.example.gifapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifapp.data.GifApi
import com.example.gifapp.data.GifItem
import kotlinx.coroutines.launch

class GifViewModel(private val gifApi: GifApi) : ViewModel() {
    private val _gifList = MutableLiveData<List<GifItem>>()
    val gifList: LiveData<List<GifItem>> get() = _gifList

    private val _errorToast = MutableLiveData<String>()
    val errorToast: LiveData<String> get() = _errorToast

    init {
        loadGifData()
    }

    fun loadGifData() {
        viewModelScope.launch {
            try {
                val gifs = gifApi.getTrendGifs()
                _gifList.postValue(gifs.data)
            } catch (e: Exception) {
                _errorToast.postValue("Помилка завантаження GIF\n Перевірте підключення до інтернету")
            }
        }
    }

    fun searchGifs(query: String) {
        viewModelScope.launch {
            try {
                val gifs = gifApi.searchGifs(
                    query = query,
                    limit = 50,
                    offset = 0,
                    rating = "g",
                    lang = "en",
                    bundle = "messaging_non_clips"
                )
                _gifList.postValue(gifs.data)
            } catch (e: Exception) {
                _errorToast.postValue("Помилка завантаження GIF\n Перевірте підключення до інтернету")
            }
        }
    }

}
