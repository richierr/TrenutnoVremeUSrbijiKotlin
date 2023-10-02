package com.example.trenutnovremeusrbijikotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.example.trenutnovremeusrbijikotlin.network.AutomaticSyncManager
import com.example.trenutnovremeusrbijikotlin.network.RSSFeed
import com.example.trenutnovremeusrbijikotlin.network.Station
import com.example.trenutnovremeusrbijikotlin.repository.WeatherRepository
import kotlinx.coroutines.launch

class SharedPlacesViewModel(application: Application) : AndroidViewModel(application) {
    var _rssFeedData = MutableLiveData<RSSFeed>()
    val latestData: LiveData<RSSFeed> get() = _rssFeedData
    val sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext)

    init {
        viewModelScope.launch {
            AppDatabase.getDataBase(application).rssFeedDao().getLatestData()
                .collect { rssFeedData ->
                    _rssFeedData.value = rssFeedData
                }
        }
        WeatherRepository.refreshData()
    }

    fun onClick(clickedStation: Station) {
        _rssFeedData.value?.let {
            it.articleList.map { station ->
                if (station.title.equals(clickedStation.title) && station.favorite) station.favorite =
                    false else if (station.title.equals(clickedStation.title)) station.favorite =
                    true
            }
            _rssFeedData.value = it
            val listOfFavs = it.articleList.filter { station -> station.favorite }
                .map { station -> station.title }
            val favorites = listOfFavs.toHashSet()
            sharedPreferences.edit().putStringSet("favStations", favorites).commit()
        }
    }

    fun autoSyncData(interval: Long) {
        AutomaticSyncManager.scheduleSync(interval)
    }

    fun cancelAutoSyncData() {
        AutomaticSyncManager.cancelAllSync()
    }


    private fun getFavs(): HashSet<String> {
        var prefSet: HashSet<String>? =
            sharedPreferences.getStringSet("favStations", HashSet<String>())
                ?.let { HashSet(it) }
        return prefSet ?: HashSet()
    }

    fun refreshDataSync() {
        WeatherRepository.refreshData()
    }
}