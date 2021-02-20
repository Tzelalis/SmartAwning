package com.example.smartawning.data.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning

class AwningDataSourceImpl (private val repo : AwningRepository): AwningDataSource {
    override suspend fun detectAwning(ipAddress: String): DetectAwning {
        return repo.detectAwning(ipAddress)
    }

    override suspend fun getAwningConfig(): AwningConfig {
        return repo.getAwningConfig()
    }

    override suspend fun updateSunSensor(): AwningConfig {
        return repo.updateSunSensor()
    }

    override suspend fun updateRainSensor(): AwningConfig {
        return repo.updateRainSensor()
    }

    override suspend fun updateTimeProgram(): AwningConfig {
        return repo.updateTimeProgram()
    }

    override suspend fun updateAwningPosition(): AwningConfig {
        return repo.updateAwningPosition()
    }
}