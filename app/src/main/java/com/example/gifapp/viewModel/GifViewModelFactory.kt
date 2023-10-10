package com.example.gifapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gifapp.data.GifApi

class GifViewModelFactory(private val gifApi: GifApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GifViewModel::class.java)) {
            return GifViewModel(gifApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}