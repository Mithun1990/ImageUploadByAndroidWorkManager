package com.naim.imageuploadbyandroidworkmanager.response

sealed class ApiResponse {
    object Success : ApiResponse()
    object Error : ApiResponse()
}