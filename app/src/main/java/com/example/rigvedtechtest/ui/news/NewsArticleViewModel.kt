package com.example.rigvedtechtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * A container for [NewsArticles] related data to show on the UI.
 */
class NewsArticleViewModel @Inject constructor(
        private val newsRepository: NewsRepository
) : ViewModel() {

    /**
     * Loading news articles from internet and database
     */
    private fun newsArticles(): LiveData<Resource<List<NewsArticles>?>> =
            newsRepository.getNewsArticles()

    fun getNewsArticles() = newsArticles()

    /**
     * Loading news articles from internet only
     */
    private fun newsArticlesFromOnlyServer() =
            newsRepository.getNewsArticlesFromServerOnly()

    fun getNewsArticlesFromServer() = newsArticlesFromOnlyServer()

}