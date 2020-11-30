package com.example.HurixTest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.HurixTest.repository.model.news.Doc
import javax.inject.Inject

class NewsArticleViewModel @Inject constructor(
        private val newsRepository: NewsRepository
) : ViewModel() {

    private fun newsArticles(): LiveData<Resource<List<Doc>?>> =
            newsRepository.getNewsArticles()

    fun getNewsArticles() = newsArticles()

    private fun newsArticlesFromOnlyServer() =
            newsRepository.getNewsArticlesFromServerOnly()

    fun getNewsArticlesFromServer() = newsArticlesFromOnlyServer()

}