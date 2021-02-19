package com.example.smartawning.data.awning

import com.example.smartawning.domain.entity.AwningConfig

data class RemoteAwningConfig(
    val ip: String?,
    val name: String?,
    val isRainChecked: Boolean?,
    val isSunnyChecked: Boolean?,
    val isTimeChecked: Boolean?,
    val timeStart: String?,
    val timeEnd: String?
)

fun RemoteAwningConfig.toAwningConfig(): AwningConfig {
    return AwningConfig(
        ip ?: "",
        name ?: "",
        isRainChecked ?: false,
        isSunnyChecked ?: false,
        isTimeChecked ?: false,
        timeStart ?: "",
        timeEnd ?: "",
    )
}