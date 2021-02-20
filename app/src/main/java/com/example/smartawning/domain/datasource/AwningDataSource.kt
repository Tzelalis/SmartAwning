package com.example.smartawning.domain.datasource

import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning

interface AwningDataSource {

    suspend fun detectAwning(ipAddress : String) : DetectAwning

    suspend fun getAwningConfig(ipAddress : String) : AwningConfig

    suspend fun updateSunSensor() : AwningConfig

    suspend fun updateRainSensor() : AwningConfig

    suspend fun updateTimeProgram() : AwningConfig

    suspend fun updateAwningPosition() : AwningConfig

}