package com.example.smartawning.data.awning

import com.example.smartawning.domain.entity.AwningConfig

interface AwningRepository {
    suspend fun getAwningConfig() : AwningConfig
}