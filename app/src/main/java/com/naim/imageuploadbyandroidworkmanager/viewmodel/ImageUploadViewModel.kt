package com.naim.imageuploadbyandroidworkmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.work.*
import com.google.gson.Gson
import com.naim.imageuploadbyandroidworkmanager.requestmodel.UploadFileRequestModel
import com.naim.imageuploadbyandroidworkmanager.workers.ImageUploadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageUploadViewModel @Inject constructor(
    private val workManager: WorkManager,
    private val gson: Gson
) :
    ViewModel() {

    fun uploadImage() {
        val list = listOf("A", "B", "C", "D", "E", "F")
        val requestModel = UploadFileRequestModel().apply { this.files.addAll(list) }
        val data = workDataOf("data" to gson.toJson(requestModel))
        val workRequest =
            OneTimeWorkRequest.Builder(ImageUploadWorker::class.java).setInputData(data)
                .setConstraints(
                    Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                )
                .addTag("UPLOAD").build()
        workManager.cancelAllWorkByTag("UPLOAD")
        workManager.enqueue(workRequest)
    }
}