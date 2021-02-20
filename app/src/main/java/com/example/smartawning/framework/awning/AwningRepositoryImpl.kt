package com.example.smartawning.framework.awning

import com.example.smartawning.data.awning.*
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning
import com.example.smartawning.domain.entity.SensorResponse
import com.example.smartawning.utils.apiCall

class AwningRepositoryImpl(private val api: AwningApi) : AwningRepository {
    override suspend fun detectAwning(ipAddress: String): DetectAwning {
        return apiCall {
            api.detectAwning(ipAddress)
        }.toDetectAwning()
    }

    override suspend fun getAwningConfig(ipAddress: String): AwningConfig {
        return apiCall {
            api.getAwningConfig()
        }.toAwningConfig()
    }

    override suspend fun updateRainSensor(ipAddress: String, isEnable: Boolean): SensorResponse {
        return apiCall {
            api.updateRainSensor(isEnable)
        }.toSensorResponse()
    }

    override suspend fun updateSunSensor(ipAddress: String, isEnable: Boolean): SensorResponse {
        return apiCall {
            api.updateSunSensor(isEnable)
        }.toSensorResponse()
    }

    override suspend fun updateTimeProgram(
        ipAddress: String,
        isEnable: Boolean,
        startHour: String,
        startMin: String,
        stopHour: String,
        stopMin: String
    ): SensorResponse {
        return apiCall {
            api.updateTimeProgram(isEnable, startHour, startMin, stopHour, stopMin)
        }.toSensorResponse()
    }

    override suspend fun updateAwningPosition(): AwningConfig {
        TODO("Not yet implemented")
    }
}