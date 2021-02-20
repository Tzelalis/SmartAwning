package com.example.smartawning.framework.awning

import com.example.smartawning.data.awning.AwningRepository
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
            api.getAwningConfig(ipAddress)
        }.toAwningConfig()
    }

    override suspend fun updateRainSensor(): AwningConfig {
        TODO("Not yet implemented")
    }

    override suspend fun updateSunSensor(): AwningConfig {
        TODO("Not yet implemented")
    }

    override suspend fun updateTimeProgram(): AwningConfig {
        TODO("Not yet implemented")
    }

    override suspend fun updateAwningPosition(): AwningConfig {
        TODO("Not yet implemented")
    }
}