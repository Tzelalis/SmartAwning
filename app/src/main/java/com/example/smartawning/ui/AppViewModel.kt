package com.example.smartawning.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() : BaseViewModel() {
    private var _showError = MutableLiveData<String>()
    val showError : LiveData<String> = _showError

    fun setError(message : String)  {
        _showError.value = message
    }
}