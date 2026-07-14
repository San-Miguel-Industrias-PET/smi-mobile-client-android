package com.grupo3.smi_mobile_client_android.di

import com.grupo3.smi_mobile_client_android.data.remote.datasource.AuthRemoteDataSource
import com.grupo3.smi_mobile_client_android.data.remote.datasource.ComunicadoRemoteDataSource
import com.grupo3.smi_mobile_client_android.data.repository.AuthRepositoryImpl
import com.grupo3.smi_mobile_client_android.data.repository.ComunicadoRepositoryImpl
import com.grupo3.smi_mobile_client_android.domain.repository.AuthRepository
import com.grupo3.smi_mobile_client_android.domain.repository.ComunicadoRepository

class RepositoryModule(private val networkModule: NetworkModule) {
    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(AuthRemoteDataSource(networkModule.apiService))
    }

    val comunicadoRepository: ComunicadoRepository by lazy {
        ComunicadoRepositoryImpl(ComunicadoRemoteDataSource(networkModule.apiService))
    }
}
