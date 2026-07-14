package com.grupo3.smi_mobile_client_android.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ComunicadoDto(
    val id: String,
    @SerializedName("titular")
    val titulo: String,
    val categoria: String,
    @SerializedName("anio")
    val anio: Int,
    val resumen: String,
    val contenido: String? = null,
    val autor: String? = null
)
