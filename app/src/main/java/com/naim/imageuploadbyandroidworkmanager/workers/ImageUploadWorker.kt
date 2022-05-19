package com.naim.imageuploadbyandroidworkmanager.workers

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.naim.imageuploadbyandroidworkmanager.R
import com.naim.imageuploadbyandroidworkmanager.extension.createChannelForOreo
import com.naim.imageuploadbyandroidworkmanager.repository.DummyRepository
import com.naim.imageuploadbyandroidworkmanager.requestmodel.UploadFileRequestModel
import com.naim.imageuploadbyandroidworkmanager.response.ApiResponse
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ImageUploadWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val gson: Gson,
    private val dummyRepository: DummyRepository
) :
    CoroutineWorker(context, params) {
    private val UPLOAD_NOTIFICATION_CHANNEL_ID = "UPLOAD_NOTIFICATION_CHANNEL_ID"
    private val UPLOAD_NOTIFICATION_ID = 1
    override suspend fun doWork(): Result {
        return try {
            println("Upload $runAttemptCount")
            uploadStartingNotificationBuilder()
            val data = gson.fromJson(
                inputData.getString("data"),
                UploadFileRequestModel::class.java
            )

            var count = 0
            while (data.files.size > count) {
                val response = dummyRepository.dummyFileUpload()
                when (response) {
                    is ApiResponse.Success -> {
                        println("Upload ${data.files[count]}")
                    }
                    else -> {
                        throw Exception()
                    }
                }
                count++
            }
            cancelNotification()
            uploadCompletedOrFailedNotificationBuilder(context.getString(R.string.upload_completed))
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            println("Upload $runAttemptCount")
            if (runAttemptCount > 3) {
                cancelNotification()
                uploadCompletedOrFailedNotificationBuilder(context.getString(R.string.upload_failed))
                Result.failure()
            } else {
                cancelNotification()
                Result.retry()
            }
        }
    }

    private fun uploadStartingNotificationBuilder() {
        val channelId = UPLOAD_NOTIFICATION_CHANNEL_ID
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true)
            .setContentTitle(context.getString(R.string.uploading)).setOngoing(true)
            .setProgress(0, 0, true).build()
        startNotification(notificationBuilder, channelId)
    }

    private fun uploadCompletedOrFailedNotificationBuilder(value: String) {
        val channelId = UPLOAD_NOTIFICATION_CHANNEL_ID
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true)
            .setContentTitle(value).setContentIntent(null)
        startNotification(notificationBuilder, channelId)
    }

    private fun startNotification(
        notificationBuilder: NotificationCompat.Builder,
        channelId: String
    ) {
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Since android Oreo notification channel is needed.
        notificationManager.createChannelForOreo(channelId)
        notificationManager.notify(
            UPLOAD_NOTIFICATION_ID,
            notificationBuilder.build()
        )
    }

    private fun cancelNotification() {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(UPLOAD_NOTIFICATION_ID)
    }
}