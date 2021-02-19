package com.example.smartawning.framework.awning

import com.example.smartawning.data.awning.RemoteAwningConfig
import retrofit2.Response
import retrofit2.http.GET

interface AwningApi {

    @GET("/44242")
    suspend fun getAwningConfig(): Response<RemoteAwningConfig>
}