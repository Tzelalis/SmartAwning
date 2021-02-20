package com.example.smartawning.usecase.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.SensorResponse

class UpdateTimeProgramUseCase(
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(
        ipAddress: String,
        isEnable: Boolean,
        startHour: String,
        startMin: String,
        stopHour: String,
        stopMin: String
    ): SensorResponse {
        return dataSource.updateTimeProgram(ipAddress, isEnable, startHour, startMin, stopHour, stopMin)
    }
}