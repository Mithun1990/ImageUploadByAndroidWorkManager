package com.naim.imageuploadbyandroidworkmanager.repository

import com.naim.imageuploadbyandroidworkmanager.response.ApiResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor() : DummyRepository {
    override suspend fun dummyFileUpload(): ApiResponse {
        delay(5000)
        return ApiResponse.Error
    }
}