package com.example.gifapp.data

import retrofit2.http.GET

const val API_KEY = "84AYDTSEbftF6btCZ3reZVxsuVAMOpJ7"

interface GifApi {
    @GET("gifs/trending?api_key=$API_KEY")
    suspend fun getTrendGifs(): GifData
}
