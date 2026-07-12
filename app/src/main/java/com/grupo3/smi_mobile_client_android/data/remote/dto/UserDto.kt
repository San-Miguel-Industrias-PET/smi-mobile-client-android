package com.grupo3.smi_mobile_client_android.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: String,
    val dni: String,
    val nombres: String,
    @SerializedName("apellido_paterno") val apellidoPaterno: String,
    @SerializedName("apellido_materno") val apellidoMaterno: String,
    val edad: Int,
    val puesto: String,
    val area: String
)
