package com.example.trenutnovremeusrbijikotlin.network

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AutoSyncWorker(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {
    override fun doWork(): Result {
        return Result.success()
    }
}