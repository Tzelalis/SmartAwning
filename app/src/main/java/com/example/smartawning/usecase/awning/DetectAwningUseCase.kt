package com.example.smartawning.usecase.awning

import com.example.smartawning.domain.datasource.AwningDataSource
import com.example.smartawning.domain.entity.DetectAwning

class DetectAwningUseCase (
    private val dataSource: AwningDataSource
) {
    suspend operator fun invoke(ipAddress : String): DetectAwning {
        return dataSource.detectAwning(ipAddress)
    }
}