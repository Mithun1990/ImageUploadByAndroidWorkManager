package com.naim.imageuploadbyandroidworkmanager.workers.factory

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.naim.imageuploadbyandroidworkmanager.workers.PeriodicImageUploadWorker

class PeriodicImageUploadWorkerFactory constructor(private val gson: Gson) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            PeriodicImageUploadWorker::class.java.name -> {
                PeriodicImageUploadWorker(appContext, workerParameters, gson)
            }
            else -> {
                null
            }
        }
    }
}