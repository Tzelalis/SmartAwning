package com.example.smartawning.domain.datasource

import com.example.smartawning.domain.entity.AwningConfig

interface AwningDataSource {
    suspend fun getAwningConfig() : AwningConfig
}