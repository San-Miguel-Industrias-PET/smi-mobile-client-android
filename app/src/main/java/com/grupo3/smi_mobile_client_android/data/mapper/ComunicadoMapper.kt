package com.grupo3.smi_mobile_client_android.data.mapper

import com.grupo3.smi_mobile_client_android.data.remote.dto.ComunicadoDto
import com.grupo3.smi_mobile_client_android.domain.model.Comunicado

object ComunicadoMapper {
    fun toDomain(dto: ComunicadoDto): Comunicado = Comunicado(
        id = dto.id,
        titulo = dto.titulo,
        categoria = dto.categoria,
        fecha = dto.anio.toString(),
        resumen = dto.resumen,
        contenido = dto.contenido ?: "",
        autor = dto.autor ?: ""
    )
}
