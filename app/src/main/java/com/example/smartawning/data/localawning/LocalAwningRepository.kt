package com.example.smartawning.data.localawning

import com.example.smartawning.domain.entity.AwningEntity

interface LocalAwningRepository {
    suspend fun getAllAwnings() : List<AwningEntity>

    suspend fun deleteAwning(awning : AwningEntity)

    suspend fun insertAwning(awning: AwningEntity)

    suspend fun updateAwning(awning: AwningEntity)

    suspend fun getAllByMac(mac : String) : List<AwningEntity>
}