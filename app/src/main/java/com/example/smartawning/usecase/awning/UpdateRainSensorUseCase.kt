package com.example.smartawning.usecase.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.AwningConfig

class UpdateRainSensorUseCase (
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(ipAddress : String, isEnable : Boolean): AwningConfig {
        return dataSource.updateRainSensor()
    }
}