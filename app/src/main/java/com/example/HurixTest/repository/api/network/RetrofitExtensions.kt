package com.example.HurixTest

import retrofit2.Response
import retrofit2.Retrofit

inline fun <reified T> Retrofit.create(): T = create(T::class.java)

fun <ResultType> Response<ResultType>.toResource(): Resource<ResultType> {
    val error = errorBody()?.toString() ?: message()
    return when {
        isSuccessful -> {
            val body = body()
            when {
                body != null -> Resource.success(body)
                else -> Resource.error(error)
            }
        }
        else -> Resource.error(error)
    }
}