package com.grupo3.smi_mobile_client_android.data.remote.datasource

import com.google.gson.Gson
import com.grupo3.smi_mobile_client_android.core.network.ApiService
import com.grupo3.smi_mobile_client_android.data.remote.dto.ApiErrorBody
import com.grupo3.smi_mobile_client_android.data.remote.dto.ComunicadoDto

class ComunicadoRemoteDataSource(private val apiService: ApiService) {

    private val gson = Gson()

    suspend fun getComunicados(categoria: String?): List<ComunicadoDto> {
        val catQuery = if (categoria == "Todos") null else categoria
        val response = apiService.getNoticias(categoria = catQuery)
        val items = response.body()?.takeIf { it.success }?.data?.items
        if (items != null) return items

        val mensaje = parseErrorMessage(response.errorBody()?.string()) ?: ERROR_GENERICO
        throw IllegalStateException(mensaje)
    }

    suspend fun getComunicadoPorId(id: String): ComunicadoDto {
        val response = apiService.getNoticiaPorId(id)
        val data = response.body()?.takeIf { it.success }?.data
        if (data != null) return data

        val mensaje = parseErrorMessage(response.errorBody()?.string()) ?: ERROR_DETALLE
        throw IllegalStateException(mensaje)
    }

    private fun parseErrorMessage(json: String?): String? {
        if (json.isNullOrBlank()) return null
        return runCatching { gson.fromJson(json, ApiErrorBody::class.java) }
            .getOrNull()
            ?.error
            ?.message
    }

    private companion object {
        const val ERROR_GENERICO = "Error al obtener noticias"
        const val ERROR_DETALLE = "Error al obtener el detalle de la noticia"
    }
}
