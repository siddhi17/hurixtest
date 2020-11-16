package com.example.rigvedtechtest

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.rigvedtechtest.repository.Response
import com.kotlin.mvvm.repository.api.ApiServices

import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 */

@Singleton
class NewsRepository @Inject constructor(
        private val newsDao: NewsArticlesDao,
        private val apiServices: ApiServices, private val context: Context,
        private val appExecutors: AppExecutors = AppExecutors()
) {

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    fun getNewsArticles(): LiveData<Resource<List<NewsArticles>?>> {

        val data = HashMap<String, String>()
        data.put("apiKey", "BuildConfig.NEWS_API_KEY")

        return object : NetworkAndDBBoundResource<List<NewsArticles>, Response>(appExecutors) {
            override fun saveCallResult(item: Response) {
                if (!item.articles.isEmpty()) {
                    newsDao.deleteAllArticles()
                    newsDao.insertArticles(item.articles)
                }
            }

            override fun shouldFetch(data: List<NewsArticles>?) =
                    (ConnectivityUtil.isConnected(context))

            override fun loadFromDb() = newsDao.getNewsArticles()

            override fun createCall() =
                    apiServices.getNewsSource(data)

        }.asLiveData()
    }

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     * LiveData<Resource<NewsSource>>
     */
    fun getNewsArticlesFromServerOnly():
            LiveData<Resource<Response>> {

        val data = HashMap<String, String>()
        data.put("apiKey", "28679d41d4454bffaf6a4f40d4b024cc")

        return object : NetworkResource<Response>() {
            override fun createCall(): LiveData<Resource<Response>> {
                return apiServices.getNewsSource(data)
            }

        }.asLiveData()
    }

}