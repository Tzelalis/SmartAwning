package com.example.smartawning.ui.awningdetails

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.smartawning.domain.entity.AppDatabase
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.domain.entity.AwningEntity
import com.example.smartawning.usecase.awning.AwningConfigUseCase
import com.example.smartawning.usecase.localawning.DeleteLocalAwningUseCase
import com.example.smartawning.usecase.localawning.GetAllLocalAwningsUseCase
import com.example.smartawning.usecase.localawning.InsertLocalAwningUseCase
import com.example.smartawning.usecase.localawning.UpdateLocalAwningUseCase
import com.example.vaseisapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AwningDetailsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val awningConfigUseCase: AwningConfigUseCase,
    private val getAllLocalAwningsUseCase: GetAllLocalAwningsUseCase,
    private val insertLocalAwningUseCase: InsertLocalAwningUseCase,
    private val deleteLocalAwningUseCase: DeleteLocalAwningUseCase,
    private val updateLocalAwningUseCase: UpdateLocalAwningUseCase,
    private val database: AppDatabase
) : BaseViewModel() {

    private var _awningConfig = MutableLiveData<AwningConfig>()
    val awningConfig = _awningConfig

    fun loadAwningConfig(id: String) {
        launch(true, 2000) {

            //val result = awningConfigUseCase(id)

            _awningConfig.value = AwningConfig(
                ip = "10",
                name = "paf",
                isRainChecked = false,
                isSunnyChecked = true,
                isTimeChecked = false,
                timeStart = "13:30",
                timeEnd = "14:00"
            )
        }
    }

    fun deleteLocalAwning(awning: AwningEntity) {
        launch(true){
            deleteLocalAwningUseCase(awning)
        }
    }

    fun updateLocalAwning(awning: AwningEntity) {
        launch(true)    {
            updateLocalAwningUseCase(awning)
        }
    }

}