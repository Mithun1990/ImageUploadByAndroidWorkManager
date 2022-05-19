package com.naim.imageuploadbyandroidworkmanager.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Configuration
import androidx.work.WorkManager
import com.naim.imageuploadbyandroidworkmanager.R
import com.naim.imageuploadbyandroidworkmanager.viewmodel.ImageUploadViewModel
import com.naim.imageuploadbyandroidworkmanager.workers.factory.ImageUploadWorkerFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ImageUploadViewModel by viewModels()

    @Inject
    lateinit var imageUploadWorkerFactory: ImageUploadWorkerFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureWorkManager()
        viewModel.uploadImage()
    }

    private fun configureWorkManager() {
        val configuration = Configuration.Builder().setWorkerFactory(imageUploadWorkerFactory)
            .setMinimumLoggingLevel(Log.VERBOSE).build()
        WorkManager.initialize(this, configuration)
    }

}