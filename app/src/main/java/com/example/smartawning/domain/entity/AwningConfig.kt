package com.example.smartawning.domain.entity


data class DetectAwning(
    val ip : String,
    val name : String,
    val mac : String
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