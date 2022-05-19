package com.naim.imageuploadbyandroidworkmanager.workers.factory

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.naim.imageuploadbyandroidworkmanager.repository.DummyRepository
import com.naim.imageuploadbyandroidworkmanager.workers.ImageUploadWorker

class ImageUploadWorkerFactory(val gson: Gson, val dummyRepository: DummyRepository) :
    WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return ImageUploadWorker(appContext, workerParameters, gson, dummyRepository)
    }
}