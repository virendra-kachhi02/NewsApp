package com.newsapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("country") s: String,
        @Query("apiKey") apikey: String
    ): Response<NewsResponse>
}