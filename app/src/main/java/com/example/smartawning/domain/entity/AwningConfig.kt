package com.example.smartawning.domain.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


data class DetectAwning(
    var name: String,
    var localIp: String,
    var publicId : String,
    var publicPort : String,
    val macAddress: String
)

data class AwningConfig(
    val temperature : String,
    val humidity : String,
    val position : Int,
    val isRainChecked: Boolean,
    val isSunnyChecked: Boolean,
    val isTimeChecked: Boolean,
    val timeStart: String,
    val timeEnd: String
)

data class SensorResponse(
    val code : String,
    val message : String
)