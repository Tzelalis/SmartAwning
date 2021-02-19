package com.example.smartawning.usecase.localawning

import com.example.smartawning.domain.datasource.LocalAwningDataSource
import com.example.smartawning.domain.entity.AwningEntity

class InsertLocalAwningUseCase(
    private val dataSource: LocalAwningDataSource
) {
    suspend operator fun invoke(awning: AwningEntity) {
        dataSource.insertAwning(awning)
    }
}