package com.example.gifapp.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gifapp.data.GifApi
import com.example.gifapp.viewModel.GifViewModel
import com.example.gifapp.adapter.GifAdapter

import com.example.gifapp.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GifViewModel
    private val adapter = GifAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val gifApi = retrofit.create(GifApi::class.java)

        viewModel = GifViewModel(gifApi)

        binding.rcView.adapter = adapter
        binding.rcView.layoutManager = GridLayoutManager(this, 2)

        viewModel.gifList.observe(this, Observer { list ->
            adapter.setData(list)
        })
        viewModel.errorToast.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })

    }
}
