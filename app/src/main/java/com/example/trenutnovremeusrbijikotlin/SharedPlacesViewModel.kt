package com.example.trenutnovremeusrbijikotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.example.trenutnovremeusrbijikotlin.network.RSSFeed
import com.example.trenutnovremeusrbijikotlin.network.Station
import com.example.trenutnovremeusrbijikotlin.repository.WeatherRepository
import kotlinx.coroutines.launch
import java.util.function.Consumer

class SharedPlacesViewModel(application: Application) : AndroidViewModel(application) {
    val rssFeedData: MutableLiveData<RSSFeed> = MutableLiveData()
    val sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext)

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
            sharedPreferences.edit().putStringSet("favStations", favorites).commit()
        }
    }


    private fun getFavs(): HashSet<String> {
        var prefSet: HashSet<String>? =
            sharedPreferences.getStringSet("favStations", HashSet<String>())
                ?.let { HashSet(it) }
        return prefSet ?: HashSet()
    }

    fun refreshData() {
        viewModelScope.launch {
            var favs = getFavs()
            var result = WeatherRepository.fetchData()
            result?.let {
                if (favs.isNotEmpty()) {
                    it.articleList.forEach(Consumer { station ->
                        if (favs.contains(station.title)) station.favorite = true
                    })
                }
                rssFeedData.postValue(it)
            }
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