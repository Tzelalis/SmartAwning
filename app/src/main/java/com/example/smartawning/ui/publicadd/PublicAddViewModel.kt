package com.example.smartawning.ui.publicadd

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
class PublicAddViewModel @Inject constructor(
    private val detectAwningUseCase: DetectAwningUseCase,
    private val insertLocalAwningUseCase: InsertLocalAwningUseCase,
    private val getAllAwningByMacUseCase: GetAllAwningByMacUseCase
) : BaseViewModel() {

    private var _device = MutableLiveData<DetectAwning>()
    val device: LiveData<DetectAwning> = _device

    private var _showError = MutableLiveData<String>()
    val showError : LiveData<String> = _showError

    fun insertDevice(ip: String, port: String = "", name: String) {
        launch(true) {
            val device = checkIpForDevice(ip, port)
            //todo detect timeout

            val isDeviceExist = checkIfDeviceExist(device.macAddress)

            if (isDeviceExist) {
                _showError.value = "Η συσκευή υπάρχει ήδη"
                return@launch
            }

            insertLocalAwningUseCase(
                AwningEntity(
                    localIp = device.localIp,
                    publicIp = device.publicIp,
                    publicPort = device.publicPort,
                    name = name,
                    macAddress = device.macAddress
                )
            )
        }
    }

    private suspend fun checkIpForDevice(ip: String, port: String = ""): DetectAwning {
        var address = ip

        if (port.isNotBlank()) {
            address = "$ip:$port"
        }

        return detectAwningUseCase(address)
    }

    private suspend fun checkIfDeviceExist(mac: String): Boolean {
        val result = getAllAwningByMacUseCase(mac)
        return result.isNotEmpty()
    }
}