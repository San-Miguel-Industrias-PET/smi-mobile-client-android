package com.grupo3.smi_mobile_client_android.data.repository

import com.grupo3.smi_mobile_client_android.data.mapper.ColaboradorMapper
import com.grupo3.smi_mobile_client_android.data.remote.datasource.AuthRemoteDataSource
import com.grupo3.smi_mobile_client_android.data.remote.dto.LoginRequest
import com.grupo3.smi_mobile_client_android.domain.model.Colaborador
import com.grupo3.smi_mobile_client_android.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun login(dni: String, credencial: String): Colaborador {
        val dto = remoteDataSource.login(LoginRequest(dni, credencial))
        return ColaboradorMapper.toDomain(dto)
    }
}
