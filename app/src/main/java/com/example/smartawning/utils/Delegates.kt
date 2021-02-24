package com.example.smartawning.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.properties.Delegates

object Variables {
    private var _liveNetworkConnection = MutableLiveData<Boolean>()
    val liveNetworkConnection : LiveData<Boolean> = _liveNetworkConnection

    var isNetworkConnected: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
        Log.v("TZEL", "$newValue")
        _liveNetworkConnection.postValue(newValue)
    }
}