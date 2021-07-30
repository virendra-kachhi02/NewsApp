package com.akshay.newsapp.news.domain


import com.akshay.newsapp.core.utils.httpError
import com.newsapp.NewsMapper
import com.newsapp.data.NewsArticleDb
import com.newsapp.data.NewsArticlesDao
import com.newsapp.di.ViewState
import com.newsapp.network.NewsResponse
import com.newsapp.network.NewsService
import com.newsapp.util.Constants
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 */
interface NewsRepository {

    /**
     * Gets tne cached news article from database and tries to get
     * fresh news articles from web and save into database
     * if that fails then continues showing cached data.
     */
    fun getNewsArticles(): Flow<ViewState<List<NewsArticleDb>>>

    /**
     * Gets fresh news from web.
     */
    suspend fun getNewsFromWebService(): Response<NewsResponse>
}

@Singleton
class DefaultNewsRepository @Inject constructor(
    private val newsDao: NewsArticlesDao,
    private val newsService: NewsService
) : NewsRepository, NewsMapper {

    override fun getNewsArticles(): Flow<ViewState<List<NewsArticleDb>>> = flow {
        // 1. Start with loading
        emit(ViewState.loading())

        // 2. Try to fetch fresh news from web + cache if any
        val freshNews = getNewsFromWebService()
        freshNews.body()?.articles?.toStorage()?.let(newsDao::clearAndCacheArticles)

        // 3. Get news from cache [cache is always source of truth]
        val cachedNews = newsDao.getNewsRoomArticles()
        emitAll(cachedNews.map { ViewState.success(it) })
    }
        .flowOn(Dispatchers.IO)

    override suspend fun getNewsFromWebService(): Response<NewsResponse> {
        return try {
            newsService.getAllNews(Constants.COUNTRY, Constants.NEWS_API_KEY)
        } catch (e: Exception) {
            httpError(404)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface NewsRepositoryModule {
    /* Exposes the concrete implementation for the interface */
    @Binds
    fun it(it: DefaultNewsRepository): NewsRepository
}