package com.example.smartawning.framework.awning

import com.example.smartawning.data.awning.RemoteAwningConfig
import com.example.smartawning.data.awning.RemoteDetectAwning
import com.example.smartawning.data.awning.RemoteSensorResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface AwningApi {

    @GET
    suspend fun detectAwning(@Url ipAddress: String): Response<RemoteDetectAwning>

    @GET
    suspend fun getAwningConfig(@Url url : String): Response<RemoteAwningConfig>

    @GET
    suspend fun updateRainSensor(@Url url : String, @Query("sensor") isEnable: Boolean): Response<RemoteSensorResponse>

    @GET
    suspend fun updateSunSensor(@Url url : String, @Query("sensor") isEnable: Boolean): Response<RemoteSensorResponse>

    @GET
    suspend fun updateTimeProgram(@Url url : String, @Query("enable") isEnable: Boolean): Response<RemoteSensorResponse>

    @GET
    suspend fun updateStartTimeProgram(@Url url : String, @Query("open_hour") openHour: Int, @Query("open_min") openMin: Int): Response<RemoteSensorResponse>

    @GET
    suspend fun updateEndTimeProgram(@Url url : String, @Query("close_hour") closeHour: Int, @Query("close_min") closeMin: Int): Response<RemoteSensorResponse>

    @GET
    suspend fun updateAwning(@Url url : String, @Query("awning_value_percent") position: Int): Response<RemoteSensorResponse>
}