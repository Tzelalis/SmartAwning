package com.example.smartawning.domain.datasource

import com.example.smartawning.data.awning.RemoteSensorResponse
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning
import com.example.smartawning.domain.entity.SensorResponse
import kotlinx.coroutines.flow.Flow

interface AwningDataSource {

    suspend fun detectAwning(ipAddress: String): DetectAwning

    suspend fun getAwningConfig(ipAddress: String): Flow<AwningConfig>

    suspend fun updateSunSensor(ipAddress: String, isEnable: Boolean): SensorResponse

    suspend fun updateRainSensor(ipAddress: String, isEnable: Boolean): SensorResponse

    suspend fun updateTimeProgram(
        ipAddress: String,
        isEnable: Boolean,
        startHour: String,
        startMin: String,
        stopHour: String,
        stopMin: String
    ): SensorResponse

    suspend fun updateAwningPosition(): AwningConfig

}