package com.example.smartawning.framework.awning

import com.example.smartawning.data.awning.AwningRepository
import com.example.smartawning.data.awning.RemoteSensorResponse
import com.example.smartawning.data.awning.toAwningConfig
import com.example.smartawning.data.awning.toDetectAwning
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning
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

    override suspend fun updateRainSensor(ipAddress: String, isEnable: Boolean): RemoteSensorResponse {
        return apiCall {
            api.updateRainSensor(isEnable)
        }
    }

    override suspend fun updateSunSensor(ipAddress: String, isEnable: Boolean): RemoteSensorResponse {
        return apiCall {
            api.updateSunSensor(isEnable)
        }
    }

    override suspend fun updateTimeProgram(
        ipAddress: String,
        isEnable: Boolean,
        startHour: String,
        startMin: String,
        stopHour: String,
        stopMin: String
    ): RemoteSensorResponse {
        return apiCall {
            api.updateTimeProgram(isEnable, startHour, startMin, stopHour, stopMin)
        }
    }

    override suspend fun updateAwningPosition(): AwningConfig {
        TODO("Not yet implemented")
    }
}