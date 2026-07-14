package com.grupo3.smi_mobile_client_android.data.repository

import com.grupo3.smi_mobile_client_android.data.mapper.ComunicadoMapper
import com.grupo3.smi_mobile_client_android.data.remote.datasource.ComunicadoRemoteDataSource
import com.grupo3.smi_mobile_client_android.domain.model.Comunicado
import com.grupo3.smi_mobile_client_android.domain.repository.ComunicadoRepository

class ComunicadoRepositoryImpl(
    private val remoteDataSource: ComunicadoRemoteDataSource
) : ComunicadoRepository {

    override suspend fun getComunicados(categoria: String?): List<Comunicado> {
        return remoteDataSource.getComunicados(categoria).map {
            ComunicadoMapper.toDomain(it)
        }
    }
}
