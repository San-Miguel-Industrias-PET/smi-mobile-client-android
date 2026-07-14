package com.grupo3.smi_mobile_client_android.data.remote.dto

data class NoticiaDataDto(
    val items: List<ComunicadoDto>,
    val nextKey: String? = null
)
