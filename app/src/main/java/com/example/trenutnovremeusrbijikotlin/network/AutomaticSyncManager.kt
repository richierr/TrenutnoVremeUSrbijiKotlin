package com.example.trenutnovremeusrbijikotlin.network

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.trenutnovremeusrbijikotlin.util.Constants
import java.util.concurrent.TimeUnit

object AutomaticSyncManager {
    fun scheduleSync(interval :Long){
        val workManager=WorkManager.getInstance()
        workManager.cancelAllWorkByTag(Constants.AUTOMATIC_SYNC_TAG)
        val worker= PeriodicWorkRequestBuilder<AutoSyncWorker>(interval,TimeUnit.MINUTES).build()
        workManager.enqueueUniquePeriodicWork(Constants.AUTOMATIC_SYNC_TAG,ExistingPeriodicWorkPolicy.KEEP,worker)
    }

    fun cancelAllSync() {
        val workManager=WorkManager.getInstance()
        workManager.cancelAllWorkByTag(Constants.AUTOMATIC_SYNC_TAG)
    }
}