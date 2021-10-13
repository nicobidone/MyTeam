package com.example.myteam.team

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.EMPTY_STRING
import com.example.domain.entity.PlayerEntity
import com.example.domain.entity.Position
import com.example.domain.usecase.PlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEditPlayerViewModel @Inject constructor(private val playerUseCase: PlayerUseCase) : ViewModel() {

    val missingInfoLiveData by lazy { MutableLiveData<Boolean>() }
    val playerEntity by lazy { MutableLiveData<PlayerEntity>() }

    fun updateData(name: Editable?, age: Editable?, position: List<Position>) {
        viewModelScope.launch {
            with(getEntity(name, age, position)) {
                if (playerUseCase.isValidData(this)) {
                    playerUseCase.updateData(this)
                    missingInfoLiveData.value = true
                } else {
                    missingInfoLiveData.value = false
                }
            }
        }
    }

    fun initState(it: PlayerEntity) {
        playerEntity.value = it
    }

    fun saveState(name: Editable?, age: Editable?, position: List<Position>) {
        playerEntity.value = getEntity(name, age, position)
    }

    private fun getEntity(name: Editable?, age: Editable?, positions: List<Position>) = PlayerEntity(
        playerEntity.value?.id ?: EMPTY_STRING,
        name.toString(),
        age.toString().let { if (it.isEmpty()) 0 else it.toInt() },
        positions
    )
}
