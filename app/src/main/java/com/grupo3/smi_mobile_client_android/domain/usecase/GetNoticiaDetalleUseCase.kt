package com.grupo3.smi_mobile_client_android.domain.usecase

import com.grupo3.smi_mobile_client_android.domain.model.Comunicado
import com.grupo3.smi_mobile_client_android.domain.repository.ComunicadoRepository

class GetNoticiaDetalleUseCase(private val repository: ComunicadoRepository) {
    suspend operator fun invoke(id: String): Comunicado {
        return repository.getComunicadoPorId(id)
    }
}
