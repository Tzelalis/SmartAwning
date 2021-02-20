package com.example.smartawning.usecase.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.AwningConfig
import kotlinx.coroutines.flow.Flow

class AwningConfigUseCase(
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(ipAddress: String): Flow<AwningConfig> {
        return dataSource.getAwningConfig(ipAddress)
    }
}