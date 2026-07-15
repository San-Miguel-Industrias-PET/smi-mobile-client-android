package com.grupo3.smi_mobile_client_android.domain.model

data class Comunicado(
    val id: String,
    val titulo: String,
    val categoria: String,
    val fecha: String,
    val resumen: String,
    val contenido: String = "",
    val autor: String = ""
)
