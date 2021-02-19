package com.example.smartawning.data.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.AwningConfig

class AwningDataSourceImpl (private val repo : AwningRepository): AwningDataSource {
    override suspend fun getAwningConfig(): AwningConfig {
        return repo.getAwningConfig()
    }
}