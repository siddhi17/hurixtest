package com.kotlin.mvvm.repository.api

import androidx.lifecycle.LiveData
import com.example.HurixTest.Resource
import com.example.HurixTest.repository.model.news.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiServices {

    @GET("search?q=title:DNA")
    fun getNewsSource(@QueryMap options: Map<String, String>): LiveData<Resource<Response>>

    @GET("search?q=title:DNA")
    fun getNewsSourceUsingCall(): Call<Response>

}
