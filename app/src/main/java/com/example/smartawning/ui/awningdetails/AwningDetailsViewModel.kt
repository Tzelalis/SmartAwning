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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class AwningDetailsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val awningConfigUseCase: AwningConfigUseCase,
    private val insertLocalAwningUseCase: InsertLocalAwningUseCase,
    private val deleteLocalAwningUseCase: DeleteLocalAwningUseCase,
    private val updateLocalAwningUseCase: UpdateLocalAwningUseCase,
    private val updateEnableProgramUseCase: UpdateEnableProgramUseCase,
    private val updateStartTimeProgramUseCase: UpdateStartTimeProgramUseCase,
    private val updateEndTimeProgramUseCase: UpdateEndTimeProgramUseCase,
    private val updateRainSensorUseCase: UpdateRainSensorUseCase,
    private val updateSunSensorUseCase: UpdateSunSensorUseCase,
    private val updateAwningPositionUseCase: UpdateAwningPositionUseCase,
) : BaseViewModel() {

    private var _awningConfig = MutableLiveData<AwningConfig>()
    val awningConfig: LiveData<AwningConfig> = _awningConfig

    fun loadAwningConfig(id: String) {
        launch(true) {
            awningConfigUseCase(id).collect {
                _awningConfig.value = it
            }

            //testing
            //_awningConfig.value = AwningConfig("20", "50", 30, true, true, false, "20:30", "03:30")
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
            updateSunSensorUseCase(ipAddress, isEnable)
        }
    }

    fun updateRainSensor(ipAddress: String, isEnable: Boolean) {
        launch(true) {
            updateRainSensorUseCase(ipAddress, isEnable)
        }
    }

    fun updateEnableProgram(ipAddress: String, isEnable: Boolean) {
        launch(true) {
            updateEnableProgramUseCase(ipAddress, isEnable)
        }
    }

    fun updateStartTimeProgram(ipAddress: String, startTime : Int, startMin : Int)  {
        launch(true)    {
            updateStartTimeProgramUseCase(ipAddress, startTime, startMin)
        }
    }

    fun updateEndTimeProgram(ipAddress : String, endHour : Int, endMin : Int)   {
        launch(true)    {
            updateStartTimeProgramUseCase(ipAddress, endHour, endMin)
        }
    }

    fun updateAwningPosition(ipAddress: String, position: Int) {
        launch(true) {
            updateAwningPositionUseCase(ipAddress, position)
        }
    }
}