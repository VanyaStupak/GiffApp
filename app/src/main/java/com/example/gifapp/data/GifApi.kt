package com.example.gifapp.data

import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "84AYDTSEbftF6btCZ3reZVxsuVAMOpJ7"

interface GifApi {
    @GET("gifs/trending?api_key=$API_KEY")
    suspend fun getTrendGifs(): GifData

    @GET("gifs/search?api_key=$API_KEY")
    suspend fun searchGifs(
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String,
        @Query("bundle") bundle: String
    ): GifData

}
