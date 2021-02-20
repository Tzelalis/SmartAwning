package com.example.smartawning.usecase.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.AwningConfig

class UpdateAwningPositionUseCase(
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(ipAddress : String, position : Int): AwningConfig {
        return dataSource.updateAwningPosition()
    }
}