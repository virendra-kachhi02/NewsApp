package com.newsapp

import com.newsapp.data.NewsArticleDb

interface OnItemClickListener {
    fun onItemClick(newsArticleDb: NewsArticleDb)
}