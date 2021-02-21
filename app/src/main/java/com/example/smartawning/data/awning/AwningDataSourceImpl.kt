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

    override suspend fun updateEnableProgram(ipAddress: String, isEnable: Boolean): SensorResponse {
        return repo.updateTimeProgram(ipAddress, isEnable)
    }

    override suspend fun updateAwningPosition(ipAddress: String, position : Int): SensorResponse {
        return repo.updateAwningPosition(ipAddress, position)
    }

    override suspend fun updateStartTimeProgram(ipAddress: String, startHour: Int, startMin: Int): SensorResponse {
        return repo.updateStartTimeProgram(ipAddress, startHour, startMin)
    }

    override suspend fun updateEndTimeProgram(ipAddress: String, stopHour: Int, stopMin: Int): SensorResponse {
        return repo.updateEndTimeProgram(ipAddress, stopHour, stopMin)
    }
}