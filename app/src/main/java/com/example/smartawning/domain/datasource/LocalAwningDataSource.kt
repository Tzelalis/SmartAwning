package com.example.smartawning.domain.datasource

import com.example.smartawning.domain.entity.AwningEntity

interface LocalAwningDataSource {
    suspend fun getAllAwnings() : List<AwningEntity>

    suspend fun deleteAwning(awning : AwningEntity)

    suspend fun insertAwning(awning: AwningEntity)

    suspend fun updateAwning(awning: AwningEntity)
}