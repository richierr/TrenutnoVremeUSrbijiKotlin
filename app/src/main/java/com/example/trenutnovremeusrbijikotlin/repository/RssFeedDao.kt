package com.example.trenutnovremeusrbijikotlin.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trenutnovremeusrbijikotlin.network.RSSFeed
import kotlinx.coroutines.flow.Flow

@Dao
interface RssFeedDao {
    @Query("SELECT * FROM rss")
    fun getAllData(): LiveData<List<RSSFeed>>

    @Insert
    fun insertRssFeedData(rssFeed: RSSFeed)

    @Query("SELECT * FROM rss ORDER BY id DESC LIMIT 1")
    fun getLatestData(): Flow<RSSFeed>
}