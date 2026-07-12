package com.grupo3.smi_mobile_client_android.di

import com.grupo3.smi_mobile_client_android.core.network.ApiService
import com.grupo3.smi_mobile_client_android.core.network.RetrofitClient

class NetworkModule {
    val apiService: ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}
