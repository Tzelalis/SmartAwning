package com.example.smartawning.ui.awning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartawning.domain.entity.AppDatabase
import com.example.smartawning.domain.entity.AwningEntity
import com.example.smartawning.usecase.localawning.GetAllLocalAwningsUseCase
import com.example.smartawning.usecase.localawning.InsertLocalAwningUseCase
import com.example.smartawning.utils.SingleLiveEvent
import com.example.vaseisapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Appendable
import javax.inject.Inject

@HiltViewModel
class AwningViewModel @Inject constructor(
    private val database : AppDatabase,
    private val insertLocalAwningUseCase: InsertLocalAwningUseCase,
    private val getAllLocalAwningsUseCase: GetAllLocalAwningsUseCase
) : BaseViewModel() {
    private var _showAwningDetails = SingleLiveEvent<AwningEntity>()
    val showAwningDetails : LiveData<AwningEntity> = _showAwningDetails

    private var _awningList = MutableLiveData<List<AwningEntity>>()
    val awningList : LiveData<List<AwningEntity>> = _awningList


    fun showDetails(awning : AwningEntity) {
        launch(true, 100) {
            _showAwningDetails.value = awning
        }
    }

    fun loadLocalAwnings() {
        launch(true)    {
            _awningList.value = getAllLocalAwningsUseCase()!!
        }
    }

    fun insertLocalAwning(awningEntity: AwningEntity) {
        launch(true)    {
            insertLocalAwningUseCase(awningEntity)
            loadLocalAwnings()
        }
    }
}