package com.example.smartawning.ui.awningdetails

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.AwningEntity
import com.example.smartawning.usecase.awning.*
import com.example.smartawning.usecase.localawning.DeleteLocalAwningUseCase
import com.example.smartawning.usecase.localawning.InsertLocalAwningUseCase
import com.example.smartawning.usecase.localawning.UpdateLocalAwningUseCase
import com.example.vaseisapp.base.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class AwningDetailsViewModel @Inject constructor(
    private val awningConfigUseCase: AwningConfigUseCase,
    private val deleteLocalAwningUseCase: DeleteLocalAwningUseCase,
    private val updateLocalAwningUseCase: UpdateLocalAwningUseCase,
    private val updateEnableProgramUseCase: UpdateEnableProgramUseCase,
    private val updateStartTimeProgramUseCase: UpdateStartTimeProgramUseCase,
    private val updateEndTimeProgramUseCase: UpdateEndTimeProgramUseCase,
    private val updateRainSensorUseCase: UpdateRainSensorUseCase,
    private val updateSunSensorUseCase: UpdateSunSensorUseCase,
    private val updateAwningPositionUseCase: UpdateAwningPositionUseCase,
) : BaseViewModel() {

    private var _temperature = MutableLiveData<String>()
    val temperature : LiveData<String> = _temperature

    private var _humidity = MutableLiveData<String>()
    val humidity : LiveData<String> = _humidity

    private var _isRainEnable = MutableLiveData<Boolean>()
    val isRainEnable: LiveData<Boolean> = _isRainEnable

    private var _isSunEnable = MutableLiveData<Boolean>()
    val isSunEnable: LiveData<Boolean> = _isSunEnable

    private var _isProgramEnable = MutableLiveData<Boolean>()
    val isProgramEnable: LiveData<Boolean> = _isProgramEnable

    private var _programStartTime = MutableLiveData<String>()
    val programStartTime: LiveData<String> = _programStartTime

    private var _programEndTime = MutableLiveData<String>()
    val programEndTime: LiveData<String> = _programEndTime

    private var _position = MutableLiveData<Int>()
    val position: LiveData<Int> = _position

    private var _rainIndicator = MutableLiveData<Boolean>()
    val rainIndicator: LiveData<Boolean> = _rainIndicator

    private var _sunIndicator = MutableLiveData<Boolean>()
    val sunIndicator: LiveData<Boolean> = _sunIndicator

    private var _programIndicator = MutableLiveData<Boolean>()
    val programIndicator: LiveData<Boolean> = _programIndicator

    private var _positionIndicator = MutableLiveData<Boolean>()
    val positionIndicator: LiveData<Boolean> = _positionIndicator

    fun loadAwningConfig(ip: String) {
        launch(true) {
            awningConfigUseCase(ip).collect { config ->
                _temperature.value = config.temperature
                _humidity.value = config.humidity

                if(rainIndicator.value != true)
                    _isRainEnable.value = config.isRainChecked

                if(sunIndicator.value != true)
                _isSunEnable.value = config.isSunnyChecked

                if(programIndicator.value != true){
                    _isProgramEnable.value = config.isTimeChecked
                    _programStartTime.value = config.timeStart
                    _programEndTime.value = config.timeEnd
                }

                if(positionIndicator.value != true)
                    _position.value = config.position
            }
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

    fun updateSunSensor(ipAddress: String, isEnable: Boolean) {
        launch(true) {
            _sunIndicator.value = true
            updateSunSensorUseCase(ipAddress, isEnable)
            _sunIndicator.value = false
        }
    }

    fun updateRainSensor(ipAddress: String, isEnable: Boolean) {
        launch(true) {
            _rainIndicator.value = true
            val result = updateRainSensorUseCase(ipAddress, isEnable)
            _rainIndicator.value = false
        }
    }

    fun updateEnableProgram(ipAddress: String, isEnable: Boolean) {
        launch(true) {
            _programIndicator.value = true
            updateEnableProgramUseCase(ipAddress, isEnable)
            _programIndicator.value = false
        }
    }

    fun updateStartTimeProgram(ipAddress: String, startTime: Int, startMin: Int) {
        launch(true) {
            _programIndicator.value = true
            updateStartTimeProgramUseCase(ipAddress, startTime, startMin)
            _programIndicator.value = false
        }
    }

    fun updateEndTimeProgram(ipAddress: String, endHour: Int, endMin: Int) {
        launch(true) {
            _programIndicator.value = true
            updateEndTimeProgramUseCase(ipAddress, endHour, endMin)
            _programIndicator.value = false
        }
    }

    fun updateAwningPosition(ipAddress: String, position: Int) {
        launch(true) {
            _positionIndicator.value = true
            updateAwningPositionUseCase(ipAddress, position)
            _positionIndicator.value = false
        }
    }
}