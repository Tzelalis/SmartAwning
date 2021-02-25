package com.example.smartawning.ui.addawning

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartawning.domain.entity.DetectAwning
import com.example.smartawning.usecase.awning.DetectAwningUseCase
import com.example.vaseisapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class AddAwningViewModel @Inject constructor(
    private val detectAwningUseCase: DetectAwningUseCase
) : BaseViewModel() {

    private var _detectDevices = MutableLiveData<List<DetectAwning>>()
    val detectDevices: LiveData<List<DetectAwning>> = _detectDevices

    private val _detected = MutableLiveData<DetectAwning>()
    val detected: LiveData<DetectAwning> = _detected

    private val _stopRotationAnimation = MutableLiveData<Unit>()
    val stopRotationAnimation: LiveData<Unit> = _stopRotationAnimation

    fun scanLocalNetwork(ipAddress: String) {
        launch(true) {
            val list = mutableListOf<DetectAwning>()
            for (i in 0..255) {
                // build the next IP address
                var addr: String = ipAddress
                addr = addr.substring(0, addr.lastIndexOf('.') + 1) + i

                detect(addr)

                //Log.v("PING", "Ping to address: $addr")
            }
            //_stopRotationAnimation.value = Unit
        }
    }

    private suspend fun detect(addr: String) {
        launch(true) {
            try {
                val result = detectAwningUseCase(addr)

                _detected.value = result

                //Log.v("PING", "Success $result")
            } catch (ex: UnknownHostException) {
                //Log.v("PING", "UnknownHostException " + ex.message.toString())
            } catch (ex: IOException) {
                //Log.v("PING", "IOException " + ex.message.toString())
            }
        }
    }
}