package com.grupo3.smi_mobile_client_android.core.network

import com.grupo3.smi_mobile_client_android.core.utils.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
