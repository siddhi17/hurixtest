package com.example.rigvedtechtest.repository

import com.example.rigvedtechtest.NewsArticles
import com.google.gson.annotations.SerializedName

/**
 * News source model describing the source details
 * and the articles under it.
 */
class Response {
    @SerializedName("numFound")
    var title: String? = null

    @SerializedName("start")
    var description: String? = null

    @SerializedName("maxScore")
    var publishedAt: String? = null

    @SerializedName("response")
    var response: Response? = null

    @SerializedName("docs")
    var articles: List<NewsArticles> = emptyList()

}