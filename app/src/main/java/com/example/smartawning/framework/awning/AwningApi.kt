package com.example.smartawning.framework.awning

import com.example.smartawning.data.awning.RemoteAwningConfig
import com.example.smartawning.data.awning.RemoteDetectAwning
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AwningApi {

    @GET("/device/detect")
    suspend fun detectAwning(ipAddress : String, @Query("smart") type : String = "awning") : Response<RemoteDetectAwning>

    @GET("/44242")
    suspend fun getAwningConfig(): Response<RemoteAwningConfig>

    @GET("")
    suspend fun updateRainSensor()

    @GET("")
    suspend fun updateSunSensor()

    @GET("")
    suspend fun updateTimeProgram()

    @GET("")
    suspend fun updateAwning()
}