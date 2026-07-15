package com.grupo3.smi_mobile_client_android.domain.usecase

import com.grupo3.smi_mobile_client_android.domain.model.Colaborador
import com.grupo3.smi_mobile_client_android.domain.repository.AuthRepository

class GetColaboradorActualUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(): Colaborador? = authRepository.getColaboradorActual()
}
