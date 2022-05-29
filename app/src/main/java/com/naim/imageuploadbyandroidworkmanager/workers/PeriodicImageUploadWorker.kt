package com.naim.imageuploadbyandroidworkmanager.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.naim.imageuploadbyandroidworkmanager.requestmodel.UploadFileRequestModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PeriodicImageUploadWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val parameters: WorkerParameters,
    private val gson: Gson
) : CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result {
        return try {
            val data = gson.fromJson(
                inputData.getString("data"),
                UploadFileRequestModel::class.java
            )
            println("Periodic request executed ${data.files.size}")
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            println("Periodic request error")
            Result.failure()
        }
    }
}