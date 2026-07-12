package com.grupo3.smi_mobile_client_android.data.remote.datasource

import com.grupo3.smi_mobile_client_android.core.network.ApiService
import com.grupo3.smi_mobile_client_android.data.remote.dto.ColaboradorDto
import com.grupo3.smi_mobile_client_android.data.remote.dto.LoginRequest
import kotlinx.coroutines.delay

// Mock temporal: aún no hay backend desplegado (ver T11 en la planificación del proyecto).
// Cuando el servicio esté disponible, reemplazar el cuerpo de login() por la llamada real:
// apiService.login(request).data ?: throw IllegalStateException("Respuesta vacía del servidor")
class AuthRemoteDataSource(private val apiService: ApiService) {

    suspend fun login(request: LoginRequest): ColaboradorDto {
        delay(NETWORK_DELAY_MS)
        require(request.dni == MOCK_DNI && request.credencial == MOCK_CREDENCIAL) {
            ERROR_CREDENCIALES_INVALIDAS
        }
        return ColaboradorDto(
            dni = request.dni,
            nombreCompleto = MOCK_NOMBRE,
            token = "mock-token-${request.dni}"
        )
    }

    private companion object {
        const val NETWORK_DELAY_MS = 900L
        const val MOCK_DNI = "12345678"
        const val MOCK_CREDENCIAL = "smi2026"
        const val MOCK_NOMBRE = "Christian Rojas"
        const val ERROR_CREDENCIALES_INVALIDAS =
            "Credenciales incorrectas. Verifique sus datos o contacte a TI"
    }
}
