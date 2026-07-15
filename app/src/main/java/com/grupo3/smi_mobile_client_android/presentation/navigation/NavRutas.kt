package com.grupo3.smi_mobile_client_android.presentation.navigation

object NavRutas {
    const val LOGIN = "login"
    const val HOME = "home"
    const val DETALLE = "detalle/{id}"
    const val PERFIL = "perfil"

    fun detalle(id: String) = "detalle/$id"
}
