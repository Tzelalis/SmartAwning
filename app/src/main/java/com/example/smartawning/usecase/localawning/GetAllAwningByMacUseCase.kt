package com.example.smartawning.usecase.localawning

import com.example.smartawning.domain.datasource.LocalAwningDataSource
import com.example.smartawning.domain.entity.AwningEntity

class GetAllAwningByMacUseCase (
    private val dataSource: LocalAwningDataSource
) {
    suspend operator fun invoke(mac : String): List<AwningEntity> {
        return dataSource.getAllByMac(mac)
    }
}