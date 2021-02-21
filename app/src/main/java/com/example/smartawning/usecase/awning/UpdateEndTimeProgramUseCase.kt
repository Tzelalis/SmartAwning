package com.example.smartawning.usecase.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.SensorResponse

class UpdateEndTimeProgramUseCase (
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(ipAddress: String, endHour : Int, endMin : Int): SensorResponse {
        return dataSource.updateEndTimeProgram(ipAddress, endHour, endMin)
    }
}