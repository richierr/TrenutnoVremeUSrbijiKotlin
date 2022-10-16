package com.example.trenutnovremeusrbijikotlin.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.trenutnovremeusrbijikotlin.network.RSSFeed
import com.example.trenutnovremeusrbijikotlin.network.ServiceGenerator
import kotlinx.coroutines.launch

object WeatherRepository {

    suspend fun fetchData(): RSSFeed? {
        try {
            var rssFeed=ServiceGenerator.instance.getFeed()
            return rssFeed
        }catch (cause :Throwable){
            println("RRRR " +cause.message)

        }
        return null
    }

}