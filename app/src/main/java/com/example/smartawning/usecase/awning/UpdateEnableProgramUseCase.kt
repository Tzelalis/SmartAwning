package com.example.smartawning.usecase.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.SensorResponse

class UpdateEnableProgramUseCase(
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(
        ipAddress: String,
        isEnable: Boolean,
    ): SensorResponse {
        return dataSource.updateEnableProgram(ipAddress, isEnable)
    }
}