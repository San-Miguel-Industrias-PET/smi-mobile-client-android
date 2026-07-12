package com.grupo3.smi_mobile_client_android.presentation.event

sealed interface UiEvent {
    val mensaje: String

    data class Success(override val mensaje: String) : UiEvent
    data class Error(override val mensaje: String) : UiEvent
    data class Warning(override val mensaje: String) : UiEvent
}
