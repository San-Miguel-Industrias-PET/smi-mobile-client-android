package com.grupo3.smi_mobile_client_android.di

import com.grupo3.smi_mobile_client_android.presentation.viewmodel.ComunicadoDetalleViewModel
import com.grupo3.smi_mobile_client_android.presentation.viewmodel.HomeViewModel
import com.grupo3.smi_mobile_client_android.presentation.viewmodel.LoginViewModel
import com.grupo3.smi_mobile_client_android.presentation.viewmodel.ProfileViewModel

class AppContainer {
    private val networkModule = NetworkModule()
    private val repositoryModule = RepositoryModule(networkModule)
    private val useCaseModule = UseCaseModule(repositoryModule)

    val loginViewModel: LoginViewModel by lazy {
        LoginViewModel(useCaseModule.authUseCases)
    }

    val homeViewModel: HomeViewModel by lazy {
        HomeViewModel(useCaseModule.comunicadoUseCases)
    }

    val profileViewModel: ProfileViewModel by lazy {
        ProfileViewModel(useCaseModule.authUseCases)
    }

    // Factory (no singleton): cada noticia abierta necesita su propia instancia con su id.
    fun crearComunicadoDetalleViewModel(id: String): ComunicadoDetalleViewModel {
        return ComunicadoDetalleViewModel(useCaseModule.comunicadoUseCases, id)
    }
}
