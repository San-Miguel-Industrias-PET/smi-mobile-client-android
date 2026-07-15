package com.grupo3.smi_mobile_client_android.di

import com.grupo3.smi_mobile_client_android.domain.usecase.AuthUseCases
import com.grupo3.smi_mobile_client_android.domain.usecase.ComunicadoUseCases
import com.grupo3.smi_mobile_client_android.domain.usecase.GetColaboradorActualUseCase
import com.grupo3.smi_mobile_client_android.domain.usecase.GetNoticiaDetalleUseCase
import com.grupo3.smi_mobile_client_android.domain.usecase.GetNoticiasUseCase
import com.grupo3.smi_mobile_client_android.domain.usecase.GuardarSesionUseCase
import com.grupo3.smi_mobile_client_android.domain.usecase.LoginUseCase

class UseCaseModule(private val repositoryModule: RepositoryModule) {
    val authUseCases: AuthUseCases by lazy {
        AuthUseCases(
            loginUseCase = LoginUseCase(repositoryModule.authRepository),
            guardarSesionUseCase = GuardarSesionUseCase(repositoryModule.authRepository),
            getColaboradorActualUseCase = GetColaboradorActualUseCase(repositoryModule.authRepository)
        )
    }

    val comunicadoUseCases: ComunicadoUseCases by lazy {
        ComunicadoUseCases(
            getNoticiasUseCase = GetNoticiasUseCase(repositoryModule.comunicadoRepository),
            getNoticiaDetalleUseCase = GetNoticiaDetalleUseCase(repositoryModule.comunicadoRepository)
        )
    }
}
