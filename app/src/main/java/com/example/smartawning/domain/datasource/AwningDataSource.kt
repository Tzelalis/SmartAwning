package com.example.smartawning.domain.datasource

import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning
import com.example.smartawning.domain.entity.SensorResponse
import kotlinx.coroutines.flow.Flow

interface AwningDataSource {

    suspend fun detectAwning(ipAddress: String): DetectAwning

    suspend fun getAwningConfig(ipAddress: String): Flow<AwningConfig>

    suspend fun updateSunSensor(ipAddress: String, isEnable: Boolean): SensorResponse

    suspend fun updateRainSensor(ipAddress: String, isEnable: Boolean): SensorResponse

    suspend fun updateEnableProgram(ipAddress: String, isEnable: Boolean, ): SensorResponse

    suspend fun updateAwningPosition(ipAddress: String, position : Int): SensorResponse

    suspend fun updateStartTimeProgram(ipAddress: String,  startHour: Int, startMin: Int) : SensorResponse

    suspend fun updateEndTimeProgram(ipAddress: String, stopHour: Int, stopMin: Int) : SensorResponse

}