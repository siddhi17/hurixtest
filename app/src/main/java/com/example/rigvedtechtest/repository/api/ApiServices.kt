package com.kotlin.mvvm.repository.api

import androidx.lifecycle.LiveData
import com.example.rigvedtechtest.Resource
import com.example.rigvedtechtest.repository.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Api services to communicate with server
 *
 */
interface ApiServices {

    /**
     * Fetch news articles from Google news using GET API Call on given Url
     * Url would be something like this top-headlines?country=my&apiKey=XYZ
     */
    @GET("search?q=title:DNA")
    fun getNewsSource(@QueryMap options: Map<String, String>): LiveData<Resource<Response>>


    /**
     * Fetch news articles from Google news using GET API Call on given Url
     * Using Call, By Retrofit
     */
    @GET("search?q=title:DNA")
    fun getNewsSourceUsingCall(): Call<Response>

}
