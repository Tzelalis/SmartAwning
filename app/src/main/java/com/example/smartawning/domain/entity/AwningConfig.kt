package com.example.smartawning.domain.entity

data class AwningConfig(
    val ip : String,
    val name : String,
    val isRainChecked: Boolean,
    val isSunnyChecked: Boolean,
    val isTimeChecked: Boolean,
    val timeStart: String,
    val timeEnd: String
)