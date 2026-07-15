package com.grupo3.smi_mobile_client_android.domain.usecase

data class AuthUseCases(
    val loginUseCase: LoginUseCase,
    val guardarSesionUseCase: GuardarSesionUseCase,
    val getColaboradorActualUseCase: GetColaboradorActualUseCase
)
