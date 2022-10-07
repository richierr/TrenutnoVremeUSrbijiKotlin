package com.example.trenutnovremeusrbijikotlin.network

import com.example.trenutnovremeusrbijikotlin.util.Constants
import org.xmlpull.v1.XmlPullParser
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object ServiceGenerator {
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder().baseUrl(Constants.automaticStationsUrl).addConverterFactory(
        SimpleXmlConverterFactory.create())


    private val retrofit: Retrofit = retrofitBuilder.build()

    private val retrofitServiceApi:RetrofitServiceApi=retrofit.create(RetrofitServiceApi::class.java)

    fun getRetrofitService():RetrofitServiceApi{
        return retrofitServiceApi
    }
    }



