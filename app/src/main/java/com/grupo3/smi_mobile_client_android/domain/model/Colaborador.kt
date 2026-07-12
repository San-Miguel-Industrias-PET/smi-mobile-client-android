package com.grupo3.smi_mobile_client_android.domain.model

data class Colaborador(
    val id: String,
    val dni: String,
    val nombres: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val edad: Int,
    val puesto: String,
    val area: String
)
