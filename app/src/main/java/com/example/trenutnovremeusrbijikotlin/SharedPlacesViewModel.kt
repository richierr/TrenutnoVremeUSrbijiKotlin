package com.example.trenutnovremeusrbijikotlin

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.example.trenutnovremeusrbijikotlin.network.RSSFeed
import com.example.trenutnovremeusrbijikotlin.network.Station
import com.example.trenutnovremeusrbijikotlin.repository.WeatherRepository
import kotlinx.coroutines.launch

class SharedPlacesViewModel(application: Application) : AndroidViewModel(application) {
    val rssFeedData: MutableLiveData<RSSFeed> = MutableLiveData()
    val sharedPreferencesEditor =
        PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext)
            .edit()

    fun onClick(clickedStation: Station) {
        rssFeedData.value?.let {
            it.articleList.map { station ->
                if (station.title.equals(clickedStation.title) && station.favorite) station.favorite =
                    false else if (station.title.equals(clickedStation.title)) station.favorite =
                    true
            }
            rssFeedData.value = it
            val listOfFavs = it.articleList.filter { station -> station.favorite }
                .map { station -> station.title }
            val favorites = listOfFavs.toHashSet()
            sharedPreferencesEditor.putStringSet("favStations", favorites).commit()
        }
    }


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