package com.grupo3.smi_mobile_client_android.data.mapper

import com.grupo3.smi_mobile_client_android.data.remote.dto.UserDto
import com.grupo3.smi_mobile_client_android.domain.model.Colaborador

object ColaboradorMapper {
    fun toDomain(dto: UserDto): Colaborador = Colaborador(
        id = dto.id,
        dni = dto.dni,
        nombres = dto.nombres,
        apellidoPaterno = dto.apellidoPaterno,
        apellidoMaterno = dto.apellidoMaterno,
        edad = dto.edad,
        puesto = dto.puesto,
        area = dto.area
    )
}
