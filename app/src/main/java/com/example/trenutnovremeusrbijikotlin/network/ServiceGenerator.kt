package com.example.trenutnovremeusrbijikotlin.network

import android.util.Log
import com.example.trenutnovremeusrbijikotlin.util.Constants
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.create

object ServiceGenerator {

    private val okHttpClient1 = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder().method(original.method(), original.body())
            Log.d("req log", original.toString())
            val request = requestBuilder.build()
            chain.proceed(request)
        }.addInterceptor(OkHttpProfilerInterceptor()).addInterceptor(HttpLoggingInterceptor())
        .build()


    val instance: RetrofitServiceApi by lazy {
        val retrofit =
            Retrofit.Builder()
                .client(okHttpClient1)
                .baseUrl(Constants.automaticStationsUrl)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
        retrofit.create()
    }
}



