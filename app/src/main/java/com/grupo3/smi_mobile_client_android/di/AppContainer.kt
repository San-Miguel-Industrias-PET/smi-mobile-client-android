package com.grupo3.smi_mobile_client_android.di

import com.grupo3.smi_mobile_client_android.presentation.viewmodel.LoginViewModel

class AppContainer {
    private val networkModule = NetworkModule()
    private val repositoryModule = RepositoryModule(networkModule)
    private val useCaseModule = UseCaseModule(repositoryModule)

    val loginViewModel: LoginViewModel by lazy {
        LoginViewModel(useCaseModule.authUseCases)
    }
}
