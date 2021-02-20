package com.example.smartawning.data.awning

import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning

interface AwningRepository {
    suspend fun detectAwning(ipAddress : String) : DetectAwning

    suspend fun getAwningConfig(ipAddress : String) : AwningConfig

    suspend fun updateRainSensor(ipAddress : String, isEnable : Boolean) : RemoteSensorResponse

    suspend fun updateSunSensor(ipAddress : String, isEnable : Boolean) : RemoteSensorResponse

    suspend fun updateTimeProgram(ipAddress: String, isEnable: Boolean, startHour : String, startMin : String, stopHour : String, stopMin : String) : RemoteSensorResponse

    suspend fun updateAwningPosition() : AwningConfig
}