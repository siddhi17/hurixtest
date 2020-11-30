package com.example.HurixTest

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.HurixTest.repository.model.news.Doc
import com.example.HurixTest.repository.model.news.Response
import com.kotlin.mvvm.repository.api.ApiServices

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
        private val newsDao: NewsArticlesDao,
        private val apiServices: ApiServices, private val context: Context,
        private val appExecutors: AppExecutors = AppExecutors()
) {


    fun getNewsArticles(): LiveData<Resource<List<Doc>?>> {

        val data = HashMap<String, String>()

        return object : NetworkAndDBBoundResource<List<Doc>, Response>(appExecutors) {
            override fun saveCallResult(item: Response) {
                if (!item.response.docs.isEmpty()) {
                    newsDao.deleteAllArticles()
                    newsDao.insertArticles(item.response.docs)
                }
            }

            override fun shouldFetch(data: List<Doc>?) =
                    (ConnectivityUtil.isConnected(context))

            override fun loadFromDb() = newsDao.getNewsArticles()

            override fun createCall() =
                    apiServices.getNewsSource(data)

        }.asLiveData()
    }

    fun getNewsArticlesFromServerOnly():
            LiveData<Resource<Response>> {

        val data = HashMap<String, String>()

        return object : NetworkResource<Response>() {
            override fun createCall(): LiveData<Resource<Response>> {
                return apiServices.getNewsSource(data)
            }

        }.asLiveData()
    }

}