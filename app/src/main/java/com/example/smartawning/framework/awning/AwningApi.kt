package com.example.smartawning.framework.awning

import com.example.smartawning.data.awning.RemoteAwningConfig
import com.example.smartawning.data.awning.RemoteDetectAwning
import com.example.smartawning.data.awning.RemoteSensorResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AwningApi {

    @GET("/device/detect")
    suspend fun detectAwning(ipAddress: String, @Query("smart") type: String = "awning"): Response<RemoteDetectAwning>

    @GET("/device/info")
    suspend fun getAwningConfig(): Response<RemoteAwningConfig>

    @GET("/device/set/water")
    suspend fun updateRainSensor(@Query("sensor") isEnable: Boolean) : Response<RemoteSensorResponse>

    @GET("/device/set/light")
    suspend fun updateSunSensor(@Query("sensor") isEnable: Boolean) : Response<RemoteSensorResponse>

    @GET("/device/set/program")
    suspend fun updateTimeProgram(
        @Query("program") isEnable: Boolean,
        @Query("open_hour") openHour: String,
        @Query("open_min") openMin: String,
        @Query("close_hour") closeHour: String,
        @Query("close_min") closeMin: String
    ) : Response<RemoteSensorResponse>

    @GET("")
    suspend fun updateAwning()
}