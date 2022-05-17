package com.naim.imageuploadbyandroidworkmanager.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.naim.imageuploadbyandroidworkmanager.MyApplication
import com.naim.imageuploadbyandroidworkmanager.workers.ImageUploadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

@HiltViewModel
class ImageUploadViewModel @Inject constructor(private val context: Context) :
    ViewModel() {
    fun uploadImage() {
        val data = workDataOf("data" to 20)

        val workRequest =
            OneTimeWorkRequest.Builder(ImageUploadWorker::class.java).setInputData(data)
                .addTag("UPLOAD").build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }
}