package com.grupo3.smi_mobile_client_android.domain.repository

import com.grupo3.smi_mobile_client_android.domain.model.Comunicado

interface ComunicadoRepository {
    suspend fun getComunicados(categoria: String? = null): List<Comunicado>
    suspend fun getComunicadoPorId(id: String): Comunicado
}
