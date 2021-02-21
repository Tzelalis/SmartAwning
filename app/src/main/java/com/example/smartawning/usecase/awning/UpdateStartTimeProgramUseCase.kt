package com.example.smartawning.usecase.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.SensorResponse

class UpdateStartTimeProgramUseCase (
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(ipAddress: String, startHour : Int, startMin : Int): SensorResponse {
        return dataSource.updateStartTimeProgram(ipAddress, startHour, startMin)
    }
}