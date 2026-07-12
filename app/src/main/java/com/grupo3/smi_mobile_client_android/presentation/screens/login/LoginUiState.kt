package com.grupo3.smi_mobile_client_android.presentation.screens.login

data class LoginUiState(
    val dni: String = "",
    val credencial: String = "",
    val isLoading: Boolean = false
)
