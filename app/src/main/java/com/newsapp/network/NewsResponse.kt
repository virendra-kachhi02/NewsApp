package com.newsapp.network

import com.google.gson.annotations.SerializedName

/**
 * Describes the response from news service API.
 */
data class NewsResponse(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val totalResults: Int = 0,

    @SerializedName("articles")
    val articles: List<Article> = emptyList()
)