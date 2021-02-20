package com.example.smartawning.usecase.awning

import com.example.smartawning.data.awning.RemoteSensorResponse
import com.example.smartawning.domain.datasource.AwningDataSource

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
    ): RemoteSensorResponse {
        return dataSource.updateTimeProgram(ipAddress, isEnable, startHour, startMin, stopHour, stopMin)
    }
}