package com.example.trenutnovremeusrbijikotlin.repository

import android.util.Log
import android.widget.Toast
import com.example.trenutnovremeusrbijikotlin.AppDatabase
import com.example.trenutnovremeusrbijikotlin.TrenutnoVremeApplication
import com.example.trenutnovremeusrbijikotlin.network.RSSFeed
import com.example.trenutnovremeusrbijikotlin.network.ServiceGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WeatherRepository {

    fun refreshData() {
        val rssFeedCall = ServiceGenerator.instance.getFeed()
        rssFeedCall.enqueue(object : Callback<RSSFeed> {
            override fun onResponse(call: Call<RSSFeed>, response: Response<RSSFeed>) {
                response.body()?.let {
                    var rss = response.body() as RSSFeed
                    Toast.makeText(
                        TrenutnoVremeApplication.applicationContext(),
                        rss.channelTitle,
                        Toast.LENGTH_LONG
                    ).show()
                    CoroutineScope(Dispatchers.IO).launch {
                        AppDatabase.getDataBase(TrenutnoVremeApplication.applicationContext())
                            .rssFeedDao().insertRssFeedData(rss)
                    }
                }
            }

            override fun onFailure(call: Call<RSSFeed>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }

        })
    }
}