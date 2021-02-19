package com.example.smartawning.framework.localawning

import com.example.smartawning.data.localawning.LocalAwningRepository
import com.example.smartawning.domain.entity.AppDatabase
import com.example.smartawning.domain.entity.AwningEntity

class LocalAwningRepositoryImpl(private val database: AppDatabase) : LocalAwningRepository {
    override suspend fun getAllAwnings(): List<AwningEntity> {
        return database.userDao().getAllAwning()
    }

    override suspend fun deleteAwning(awning: AwningEntity) {
        database.userDao().deleteAwning(awning)
    }

    override suspend fun insertAwning(awning: AwningEntity) {
        database.userDao().insertAwning(awning)
    }

    override suspend fun updateAwning(awning: AwningEntity) {
        database.userDao().updateAwning(awning)
    }
}