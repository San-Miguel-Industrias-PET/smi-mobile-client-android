package com.grupo3.smi_mobile_client_android.data.repository

import com.grupo3.smi_mobile_client_android.data.mapper.ColaboradorMapper
import com.grupo3.smi_mobile_client_android.data.remote.datasource.AuthRemoteDataSource
import com.grupo3.smi_mobile_client_android.data.remote.dto.LoginRequest
import com.grupo3.smi_mobile_client_android.domain.model.Colaborador
import com.grupo3.smi_mobile_client_android.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    private var colaboradorActual: Colaborador? = null

    override suspend fun login(dni: String, credencial: String): Colaborador {
        val dto = remoteDataSource.login(LoginRequest(dni = dni, password = credencial))
        return ColaboradorMapper.toDomain(dto)
    }

    override fun getColaboradorActual(): Colaborador? = colaboradorActual

    override fun guardarColaborador(colaborador: Colaborador) {
        colaboradorActual = colaborador
    }
}
