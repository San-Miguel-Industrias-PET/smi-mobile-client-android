package com.grupo3.smi_mobile_client_android.core.network

import com.grupo3.smi_mobile_client_android.data.remote.dto.ApiResponse
import com.grupo3.smi_mobile_client_android.data.remote.dto.LoginDataDto
import com.grupo3.smi_mobile_client_android.data.remote.dto.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<LoginDataDto>>
}
