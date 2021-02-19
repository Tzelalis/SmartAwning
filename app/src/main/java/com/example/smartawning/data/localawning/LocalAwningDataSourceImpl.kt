package com.example.smartawning.data.localawning

import com.example.smartawning.domain.datasource.LocalAwningDataSource
import com.example.smartawning.domain.entity.AwningEntity

class LocalAwningDataSourceImpl(private val repo : LocalAwningRepository) : LocalAwningDataSource{
    override suspend fun getAllAwnings(): List<AwningEntity> {
        return repo.getAllAwnings()
    }

    override suspend fun deleteAwning(awning: AwningEntity) {
        repo.deleteAwning(awning)
    }

    override suspend fun insertAwning(awning: AwningEntity) {
        repo.insertAwning(awning)
    }

    override suspend fun updateAwning(awning: AwningEntity) {
        repo.updateAwning(awning)
    }
}