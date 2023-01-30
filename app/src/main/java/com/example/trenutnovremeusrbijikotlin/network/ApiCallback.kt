package com.example.trenutnovremeusrbijikotlin.network

interface ApiCallback<T> {
    fun onException(error: Throwable)

    fun onError(error: String)

    fun onSuccess(t: T)
}