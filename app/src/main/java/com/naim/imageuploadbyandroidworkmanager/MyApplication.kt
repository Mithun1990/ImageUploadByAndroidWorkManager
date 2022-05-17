package com.naim.imageuploadbyandroidworkmanager

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        var APP_CONTEXT: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        APP_CONTEXT = applicationContext
    }
}