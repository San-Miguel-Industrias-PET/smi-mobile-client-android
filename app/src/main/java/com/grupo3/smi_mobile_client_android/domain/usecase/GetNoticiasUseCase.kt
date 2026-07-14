package com.grupo3.smi_mobile_client_android.domain.usecase

import com.grupo3.smi_mobile_client_android.domain.model.Comunicado
import com.grupo3.smi_mobile_client_android.domain.repository.ComunicadoRepository

class GetNoticiasUseCase(private val repository: ComunicadoRepository) {
    suspend operator fun invoke(categoria: String? = null): List<Comunicado> {
        return repository.getComunicados(categoria)
    }
}
