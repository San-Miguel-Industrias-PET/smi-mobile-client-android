package com.grupo3.smi_mobile_client_android.data.remote.dto

// Forma real del body de error (ver funUser.openapi.yaml: 400/401 solo documentan la descripción,
// no el schema). Se parsea a mano porque Retrofit/Gson solo convierte automáticamente el body 2xx.
data class ApiErrorBody(
    val success: Boolean,
    val error: ApiErrorDetail?
)

data class ApiErrorDetail(
    val message: String
)
