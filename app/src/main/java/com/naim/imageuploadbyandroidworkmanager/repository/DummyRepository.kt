package com.naim.imageuploadbyandroidworkmanager.repository

import com.naim.imageuploadbyandroidworkmanager.response.ApiResponse

interface DummyRepository {
    suspend fun dummyFileUpload(): ApiResponse
}