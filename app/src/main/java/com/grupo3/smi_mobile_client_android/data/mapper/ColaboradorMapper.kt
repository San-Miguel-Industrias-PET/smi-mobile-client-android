package com.grupo3.smi_mobile_client_android.data.mapper

import com.grupo3.smi_mobile_client_android.data.remote.dto.ColaboradorDto
import com.grupo3.smi_mobile_client_android.domain.model.Colaborador

object ColaboradorMapper {
    fun toDomain(dto: ColaboradorDto): Colaborador = Colaborador(
        dni = dto.dni,
        nombreCompleto = dto.nombreCompleto,
        token = dto.token
    )
}
