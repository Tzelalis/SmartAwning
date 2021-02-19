package com.example.smartawning.usecase.localawning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.datasource.LocalAwningDataSource
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.AwningEntity

class GetAllLocalAwningsUseCase(
    private val dataSource: LocalAwningDataSource
) {
    suspend operator fun invoke(): List<AwningEntity> {
        return dataSource.getAllAwnings()
    }
}