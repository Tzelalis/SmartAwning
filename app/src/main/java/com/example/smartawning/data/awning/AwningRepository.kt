package com.example.smartawning.data.awning

import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning
import com.example.smartawning.domain.entity.SensorResponse

interface AwningRepository {
    suspend fun detectAwning(ipAddress : String) : DetectAwning

    suspend fun getAwningConfig(ipAddress : String) : AwningConfig

    suspend fun updateRainSensor(ipAddress : String, isEnable : Boolean) : SensorResponse

    suspend fun updateSunSensor(ipAddress : String, isEnable : Boolean) : SensorResponse

    suspend fun updateTimeProgram(ipAddress: String, isEnable: Boolean) : SensorResponse

    suspend fun updateAwningPosition(ipAddress: String, position : Int) : SensorResponse

    suspend fun updateStartTimeProgram(ipAddress: String, startHour : Int, startMin : Int) : SensorResponse

    suspend fun updateEndTimeProgram(ipAddress: String, startHour : Int, startMin : Int) : SensorResponse
}