package com.grupo3.smi_mobile_client_android.domain.repository

import com.grupo3.smi_mobile_client_android.domain.model.Colaborador

interface AuthRepository {
    suspend fun login(dni: String, credencial: String): Colaborador
    fun getColaboradorActual(): Colaborador?
    fun guardarColaborador(colaborador: Colaborador)
}
