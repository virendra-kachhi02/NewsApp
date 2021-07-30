package com.newsapp

import com.newsapp.data.NewsArticleDb
import com.newsapp.di.Mapper
import com.newsapp.network.Article

interface NewsMapper : Mapper<NewsArticleDb, Article> {
    override fun NewsArticleDb.toRemote(): Article {
        return Article(
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content,
            source = Article.Source(source.id, source.name)
        )
    }

    override fun Article.toStorage(): NewsArticleDb {
        return NewsArticleDb(
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content,
            source = NewsArticleDb.Source(source.id, source.name)
        )
    }
}