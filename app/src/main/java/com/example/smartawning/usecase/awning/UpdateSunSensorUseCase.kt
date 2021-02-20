package com.example.smartawning.usecase.awning

import com.example.smartawning.data.awning.RemoteSensorResponse
import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.AwningConfig

class UpdateSunSensorUseCase(
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(ipAddress : String, isEnable : Boolean): RemoteSensorResponse {
        return dataSource.updateSunSensor(ipAddress, isEnable)
    }
}