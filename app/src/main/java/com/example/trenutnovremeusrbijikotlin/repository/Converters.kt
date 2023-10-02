package com.example.trenutnovremeusrbijikotlin.repository

import androidx.room.TypeConverter
import com.example.trenutnovremeusrbijikotlin.network.Station
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String): List<Station> {
        val listType = object : TypeToken<List<Station>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Station>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}