package com.example.smartawning.ui.awningdetails

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.AwningEntity
import com.example.smartawning.usecase.awning.*
import com.example.smartawning.usecase.localawning.DeleteLocalAwningUseCase
import com.example.smartawning.usecase.localawning.InsertLocalAwningUseCase
import com.example.smartawning.usecase.localawning.UpdateLocalAwningUseCase
import com.example.vaseisapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AwningDetailsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val awningConfigUseCase: AwningConfigUseCase,
    private val insertLocalAwningUseCase: InsertLocalAwningUseCase,
    private val deleteLocalAwningUseCase: DeleteLocalAwningUseCase,
    private val updateLocalAwningUseCase: UpdateLocalAwningUseCase,
    private val updateTimeProgramUseCase: UpdateTimeProgramUseCase,
    private val updateRainSensorUseCase: UpdateRainSensorUseCase,
    private val updateSunSensorUseCase: UpdateSunSensorUseCase,
    private val updateAwningPositionUseCase: UpdateAwningPositionUseCase,
) : BaseViewModel() {

    private var _awningConfig = MutableLiveData<AwningConfig>()
    val awningConfig = _awningConfig

    fun loadAwningConfig(id: String) {
        launch(true) {
            val result = awningConfigUseCase(id)
            _awningConfig.value = result
        }
    }

    fun deleteLocalAwning(awning: AwningEntity) {
        launch(true) {
            deleteLocalAwningUseCase(awning)
        }
    }

    fun updateLocalAwning(awning: AwningEntity) {
        launch(true) {
            updateLocalAwningUseCase(awning)
        }
    }

    fun updateSunSensor(ipAddress : String, isEnable: Boolean) {
        launch(true) {
            updateSunSensorUseCase(ipAddress, isEnable)
        }
    }

    fun updateRainSensor(ipAddress : String, isEnable: Boolean) {
        launch(true) {
            updateRainSensorUseCase(ipAddress, isEnable)
        }
    }

    fun updateTimeProgram(ipAddress : String, isEnable: Boolean) {
        launch(true) {
            updateTimeProgramUseCase(ipAddress)
        }
    }

    fun updateAwningPosition(ipAddress : String, position: Int) {
        launch(true) {
            updateAwningPositionUseCase(ipAddress, position)
        }
    }
}