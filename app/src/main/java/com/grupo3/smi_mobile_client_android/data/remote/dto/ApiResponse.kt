package com.grupo3.smi_mobile_client_android.data.remote.dto

data class ApiResponse<T>(
    val success: Boolean,
    val data: T?,
    val message: String?,
    val timestamp: String?
)
