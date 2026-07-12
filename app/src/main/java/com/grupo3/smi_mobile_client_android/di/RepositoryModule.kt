package com.grupo3.smi_mobile_client_android.di

import com.grupo3.smi_mobile_client_android.data.remote.datasource.AuthRemoteDataSource
import com.grupo3.smi_mobile_client_android.data.repository.AuthRepositoryImpl
import com.grupo3.smi_mobile_client_android.domain.repository.AuthRepository

class RepositoryModule(private val networkModule: NetworkModule) {
    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(AuthRemoteDataSource(networkModule.apiService))
    }
}
