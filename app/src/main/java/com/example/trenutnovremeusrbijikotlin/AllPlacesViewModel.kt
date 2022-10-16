package com.example.trenutnovremeusrbijikotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trenutnovremeusrbijikotlin.network.RSSFeed
import com.example.trenutnovremeusrbijikotlin.network.ServiceGenerator
import com.example.trenutnovremeusrbijikotlin.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllPlacesViewModel : ViewModel() {
    val rssFeedData: MutableLiveData<RSSFeed> = MutableLiveData()


    fun refreshData() {
        viewModelScope.launch {
            rssFeedData.postValue(WeatherRepository.fetchData())
            //var call: RSSFeed = ServiceGenerator.instance.getFeed()
           // println(call)
//            call.enqueue(object : Callback<String> {
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//            })
        }
    }
    // TODO: Implement the ViewModel
}