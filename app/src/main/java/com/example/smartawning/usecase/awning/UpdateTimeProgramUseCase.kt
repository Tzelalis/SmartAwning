package com.example.smartawning.usecase.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.AwningConfig

class UpdateTimeProgramUseCase (
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(ip: String): AwningConfig {
        return dataSource.updateTimeProgram()
    }
}