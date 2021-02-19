package com.example.smartawning.usecase.localawning

import com.example.smartawning.domain.datasource.LocalAwningDataSource
import com.example.smartawning.domain.entity.AwningEntity

class DeleteLocalAwningUseCase (
    private val dataSource: LocalAwningDataSource
) {
    suspend operator fun invoke(awningEntity: AwningEntity) {
        return dataSource.deleteAwning(awningEntity)
    }
}