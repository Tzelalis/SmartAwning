package com.example.smartawning.framework.awning

import com.example.smartawning.data.awning.RemoteAwningConfig
import com.example.smartawning.data.awning.RemoteDetectAwning
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AwningApi {

    @GET("/device/detect")
    suspend fun detectAwning(ipAddress : String, @Query("smart") type : String = "awning") : Response<RemoteDetectAwning>

    @GET("http://192.168.2.6/device/info")
    suspend fun getAwningConfig(ipAddress : String): Response<RemoteAwningConfig>

    @GET("")
    suspend fun updateRainSensor()

    @GET("")
    suspend fun updateSunSensor()

    @GET("")
    suspend fun updateTimeProgram()

    @GET("")
    suspend fun updateAwning()
}