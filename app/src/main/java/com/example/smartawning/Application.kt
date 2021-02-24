package com.example.smartawning

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import com.example.smartawning.utils.Variables
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates

@HiltAndroidApp
class Application : Application(){

    override fun onCreate() {
        super.onCreate()

        startNetworkCallback()


    }

    private fun startNetworkCallback() {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()

        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    Variables.isNetworkConnected = true
                }
                override fun onLost(network: Network) {
                    Variables.isNetworkConnected = false
                }
            })
    }
}