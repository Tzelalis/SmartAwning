package com.example.smartawning.ui.localadd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartawning.domain.entity.AwningEntity
import com.example.smartawning.domain.entity.DetectAwning
import com.example.smartawning.usecase.awning.DetectAwningUseCase
import com.example.smartawning.usecase.localawning.GetAllAwningByMacUseCase
import com.example.smartawning.usecase.localawning.InsertLocalAwningUseCase
import com.example.vaseisapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalAddViewModel @Inject constructor(
    private val detectAwningUseCase: DetectAwningUseCase,
    private val insertLocalAwningUseCase: InsertLocalAwningUseCase,
    private val getAllAwningByMacUseCase: GetAllAwningByMacUseCase
) : BaseViewModel() {

    private var _device = MutableLiveData<DetectAwning>()
    val device: LiveData<DetectAwning> = _device

    private var _showError = MutableLiveData<String>()
    val showError: LiveData<String> = _showError

    fun insertDevice(ip: String, name: String) {
        launch(true) {
            val device = detectAwningUseCase(ip)
            //todo detect timeout

            if (getAllAwningByMacUseCase(device.macAddress).isNotEmpty()) {
                _showError.value = "Η συσκευή υπάρχει ήδη"
                return@launch
            }

            insertLocalAwningUseCase(
                AwningEntity(
                    localIp = device.localIp,
                    publicIp = device.localIp,
                    publicPort = device.publicPort,
                    name = name,
                    macAddress = device.macAddress
                )
            )
        }
    }
}