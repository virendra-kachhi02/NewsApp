package com.newsapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.akshay.newsapp.news.domain.NewsRepository
import com.newsapp.data.NewsArticleDb
import com.newsapp.di.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val query = MutableLiveData<String>("")
    private val newsArticleDb: LiveData<ViewState<List<NewsArticleDb>>> =
        newsRepository.getNewsArticles().asLiveData()

    /**
     * Return news articles to observeNotNull on the UI.
     */
    fun getNewsArticles(): LiveData<ViewState<List<NewsArticleDb>>> = newsArticleDb
}