package com.grupo3.smi_mobile_client_android.domain.usecase

import com.grupo3.smi_mobile_client_android.domain.model.Colaborador
import com.grupo3.smi_mobile_client_android.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(dni: String, credencial: String): Colaborador =
        authRepository.login(dni, credencial)
}
