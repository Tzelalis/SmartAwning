package com.example.smartawning.framework.awning

import com.example.smartawning.data.awning.AwningRepository
import com.example.smartawning.data.awning.toAwningConfig
import com.example.smartawning.data.awning.toDetectAwning
import com.example.smartawning.data.awning.toSensorResponse
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.DetectAwning
import com.example.smartawning.domain.entity.SensorResponse
import com.example.smartawning.utils.apiCall

class AwningRepositoryImpl(private val api: AwningApi) : AwningRepository {
    override suspend fun detectAwning(ipAddress: String): DetectAwning {
        return apiCall {
            val url = "http://$ipAddress/device/detect"

            api.detectAwning(url)
        }.toDetectAwning()
    }

    override suspend fun getAwningConfig(ipAddress: String): AwningConfig {
        return apiCall {
            val url = "http://$ip/device/info"
            api.getAwningConfig(url)
        }.toAwningConfig()

        //return AwningConfig("20", "50", 30, true, true, false, "20:30", "03:30")  //testing
    }

    override suspend fun updateRainSensor(ipAddress: String, isEnable: Boolean): SensorResponse {
        return apiCall {
            val url = "http://$ip/device/set/water"
            api.updateRainSensor(url, isEnable)
        }.toSensorResponse()
    }
    val ip = "192.168.2.15"
    override suspend fun updateSunSensor(ipAddress: String, isEnable: Boolean): SensorResponse {
        return apiCall {
            val url = "http://$ip/device/set/light"
            api.updateSunSensor(url, isEnable)
        }.toSensorResponse()
    }

    override suspend fun updateTimeProgram(ipAddress: String, isEnable: Boolean): SensorResponse {
        return apiCall {
            val url = "http://$ip/device/set/program"
            api.updateTimeProgram(url, isEnable)
        }.toSensorResponse()
    }

    override suspend fun updateAwningPosition(ipAddress: String, position: Int): SensorResponse {
        return apiCall {
            val url = "http://$ip/device/set/bar"
            api.updateAwning(url, position)
        }.toSensorResponse()
    }

    override suspend fun updateStartTimeProgram(ipAddress: String, startHour : Int, startMin : Int): SensorResponse {
        return apiCall {
            val url = "http://$ip/device/set/program"
            api.updateStartTimeProgram(url, startHour, startMin)
        }.toSensorResponse()
    }

    override suspend fun updateEndTimeProgram(ipAddress: String, endHour : Int, endMin : Int): SensorResponse {
        return apiCall {
            val url = "http://$ip/device/set/program"
            api.updateEndTimeProgram(url, endHour, endMin)
        }.toSensorResponse()
    }
}