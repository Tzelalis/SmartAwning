package com.example.smartawning.framework.awning

import com.example.smartawning.data.awning.AwningRepository
import com.example.smartawning.data.awning.toAwningConfig
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.utils.apiCall

class AwningRepositoryImpl(private val api: AwningApi) : AwningRepository {
    override suspend fun getAwningConfig(): AwningConfig {
        return apiCall {
            api.getAwningConfig()
        }.toAwningConfig()
    }
}