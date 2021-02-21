package com.example.smartawning.usecase.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.SensorResponse

class UpdateAwningPositionUseCase(
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(ipAddress : String, position : Int): SensorResponse {
        return dataSource.updateAwningPosition(ipAddress, position)
    }
}