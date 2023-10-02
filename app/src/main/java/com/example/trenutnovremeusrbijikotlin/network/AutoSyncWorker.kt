package com.example.trenutnovremeusrbijikotlin.network

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.trenutnovremeusrbijikotlin.repository.WeatherRepository

class AutoSyncWorker(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {
    override fun doWork(): Result {
        WeatherRepository.refreshData()
        return Result.success()
    }
}