package com.naim.imageuploadbyandroidworkmanager

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import com.naim.imageuploadbyandroidworkmanager.workers.factory.ImageUploadWorkerFactory
import com.naim.imageuploadbyandroidworkmanager.workers.factory.PeriodicImageUploadWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var imageUploadWorkerFactory: ImageUploadWorkerFactory

    @Inject
    lateinit var periodicImageUploadWorkerFactory: PeriodicImageUploadWorkerFactory

    companion object {
        var APP_CONTEXT: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        APP_CONTEXT = applicationContext
    }

    override fun getWorkManagerConfiguration(): Configuration {
        val workerFactory = DelegatingWorkerFactory()
        workerFactory.addFactory(imageUploadWorkerFactory)
        workerFactory.addFactory(periodicImageUploadWorkerFactory)
        return Configuration.Builder().setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(Log.VERBOSE).build()
    }
}