package com.example.trenutnovremeusrbijikotlin.network

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServiceApi {
    @GET("latin/osmotreni/index.rss")
    fun getFeed(): Call<RSSFeed>
}