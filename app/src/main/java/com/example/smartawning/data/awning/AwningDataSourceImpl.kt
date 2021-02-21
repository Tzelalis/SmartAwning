package com.example.smartawning.data.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning
import com.example.smartawning.domain.entity.SensorResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AwningDataSourceImpl(private val repo: AwningRepository) : AwningDataSource {
    override suspend fun detectAwning(ipAddress: String): DetectAwning {
        return repo.detectAwning(ipAddress)
    }

    override suspend fun getAwningConfig(ipAddress: String): Flow<AwningConfig> {
        return flow {
            while (true) {
                val config = repo.getAwningConfig(ipAddress)
                emit(config)
                delay(5000)
            }
        }
    }

    override suspend fun updateSunSensor(ipAddress: String, isEnable: Boolean): SensorResponse {
        return repo.updateSunSensor(ipAddress, isEnable)
    }

    override suspend fun updateRainSensor(ipAddress: String, isEnable: Boolean): SensorResponse {
        return repo.updateRainSensor(ipAddress, isEnable)
    }

    override suspend fun updateTimeProgram(
        ipAddress: String,
        isEnable: Boolean,
        startHour: String,
        startMin: String,
        stopHour: String,
        stopMin: String
    ): SensorResponse {
        return repo.updateTimeProgram(ipAddress, isEnable, startHour, startMin, stopHour, stopMin)
    }

    override suspend fun updateAwningPosition(ipAddress: String, position : Int): SensorResponse {
        return repo.updateAwningPosition(ipAddress, position)
    }
}