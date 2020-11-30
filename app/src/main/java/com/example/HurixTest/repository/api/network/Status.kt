package com.example.HurixTest

enum class Status {
    SUCCESS,
    ERROR,
    LOADING;

    fun isSuccessful() = this == SUCCESS

    fun isLoading() = this == LOADING

    fun isError() = this == ERROR
}