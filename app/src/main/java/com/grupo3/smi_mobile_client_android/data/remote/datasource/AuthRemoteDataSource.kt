package com.grupo3.smi_mobile_client_android.data.remote.datasource

import com.google.gson.Gson
import com.grupo3.smi_mobile_client_android.core.network.ApiService
import com.grupo3.smi_mobile_client_android.data.remote.dto.ApiErrorBody
import com.grupo3.smi_mobile_client_android.data.remote.dto.LoginRequest
import com.grupo3.smi_mobile_client_android.data.remote.dto.UserDto

class AuthRemoteDataSource(private val apiService: ApiService) {

    private val gson = Gson()

    suspend fun login(request: LoginRequest): UserDto {
        val response = apiService.login(request)
        val user = response.body()?.takeIf { it.success }?.data?.user
        if (user != null) return user

        val mensaje = parseErrorMessage(response.errorBody()?.string()) ?: ERROR_GENERICO
        throw IllegalStateException(mensaje)
    }

    // El body de error no lo convierte Retrofit/Gson automáticamente (solo aplica al body 2xx).
    private fun parseErrorMessage(json: String?): String? {
        if (json.isNullOrBlank()) return null
        return runCatching { gson.fromJson(json, ApiErrorBody::class.java) }
            .getOrNull()
            ?.error
            ?.message
    }

    private companion object {
        const val ERROR_GENERICO = "No se pudo iniciar sesión"
    }
}
