package com.example.rigvedtechtest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_news_articles.*
import kotlinx.android.synthetic.main.empty_layout_news_article.*
import kotlinx.android.synthetic.main.progress_layout_news_article.*


/**
 * News Article Listing Activity.
 */
class NewsArticlesActivity : BaseActivity() {

    private lateinit var adapter: NewsArticlesAdapter

    private val newsArticleViewModel by lazy {
        getViewModel<NewsArticleViewModel>()
    }

    /**
     * On Create Of Activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_articles)

        news_list.setEmptyView(empty_view)
        news_list.setProgressView(progress_view)

        adapter = NewsArticlesAdapter {
          //  Toast.makeText(this, it.description.toString(), Toast.LENGTH_SHORT)
        }
        news_list.adapter = adapter
        news_list.layoutManager = LinearLayoutManager(this)

        intent.let { getNewsOfCountry(it) }

        /**
         * View model getting API response from server using Network Bound Resource Only
         */
        /*      if (ConnectivityUtil.isConnected(this)) {
                  newsArticleViewModel.getNewsArticlesFromServer().observe(this) {
                      when {
                          it.status.isLoading() -> {

                          }
                          it.status.isSuccessful() -> {
                              it.load(news_list) {
                                  it?.let {
                                      adapter.replaceItems(it.articles)
                                  }
                              }
                          }
                          it.status.isError() -> {

                          }
                      }
                  }
              } else {
                  ToastUtil.showCustomToast(this, "No Internet Connection")
              }*/

    }

    /**
     * Get country news using Network & DB Bound Resource
     */
    private fun getNewsOfCountry(countryKey: Intent) {
        /*
        * Observing for data change, Cater DB and Network Both
        * */
        newsArticleViewModel.getNewsArticles().observe(this) {
            when {
                it.status.isLoading() -> {
                    news_list.showProgressView()
                }
                it.status.isSuccessful() -> {
                    it.load(news_list) {
                        // Update the UI as the data has changed
                        it?.let { adapter.replaceItems(it) }
                    }
                }
                it.status.isError() -> {
                    if (it.errorMessage != null)
                        Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT)
                }
            }
        }
    }
}
