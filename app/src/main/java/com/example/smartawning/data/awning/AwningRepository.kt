package com.example.smartawning.data.awning

import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning

interface AwningRepository {
    suspend fun detectAwning(ipAddress : String) : DetectAwning

    suspend fun getAwningConfig() : AwningConfig

    suspend fun updateRainSensor() : AwningConfig

    suspend fun updateSunSensor() : AwningConfig

    suspend fun updateTimeProgram() : AwningConfig

    suspend fun updateAwningPosition() : AwningConfig
}