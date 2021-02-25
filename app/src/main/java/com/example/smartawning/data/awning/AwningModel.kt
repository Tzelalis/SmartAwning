package com.example.smartawning.data.awning

import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning
import com.example.smartawning.domain.entity.SensorResponse
import com.squareup.moshi.Json

data class RemoteAwningConfig(
    val temperature : String?,
    val humidity : String?,
    @Json(name = "awning_value") val position: Int?,
    @Json(name = "water_enable") val isRainChecked: Int?,
    @Json(name = "light_enable")val isSunnyChecked: Int?,
    @Json(name = "program_enable")val isTimeProgramChecked: Int?,
    @Json(name = "program_open")val timeStart: String?,
    @Json(name = "program_close")val timeEnd: String?
)

data class RemoteDetectAwning(
    @Json(name = "name") var name: String?,
    @Json(name = "ip") var localIp: String?,
    @Json(name = "public_ip") var publicIp : String?,
    @Json(name = "public_port") var publicPort : String?,
    @Json(name = "mac_address") val macAddress: String?
)

data class RemoteSensorResponse(
    val code : String?,
    @Json(name = "text")val message : String?
)

fun RemoteAwningConfig.toAwningConfig(): AwningConfig {
    return AwningConfig(
        temperature?: "",
        humidity ?: "",
        position ?: 0,
        isRainChecked == 1,
        isSunnyChecked == 1,
        isTimeProgramChecked == 1,
        timeStart ?: "",
        timeEnd ?: "",
    )
}

fun RemoteDetectAwning.toDetectAwning(): DetectAwning {
    return DetectAwning(
        name ?: "",
        localIp ?: "",
        publicIp ?: "",
        publicPort ?: "",
        macAddress ?: ""
    )
}

fun RemoteSensorResponse.toSensorResponse() : SensorResponse    {
    return SensorResponse(
        code ?: "",
        message ?: ""
    )
}