package com.grupo3.smi_mobile_client_android.core.network

import com.grupo3.smi_mobile_client_android.core.utils.Constantes
import com.grupo3.smi_mobile_client_android.data.remote.dto.ApiResponse
import com.grupo3.smi_mobile_client_android.data.remote.dto.LoginDataDto
import com.grupo3.smi_mobile_client_android.data.remote.dto.LoginRequest
import com.grupo3.smi_mobile_client_android.data.remote.dto.NoticiaDataDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<LoginDataDto>>

    @GET(Constantes.NOTICIAS_URL)
    suspend fun getNoticias(
        @Query("categoria") categoria: String? = null,
        @Query("limit") limit: Int? = null,
    ): Response<ApiResponse<NoticiaDataDto>>
}
