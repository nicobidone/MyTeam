package com.example.myteam.team

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PlayerEntity
import com.example.domain.usecase.PlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(private val playerUseCase: PlayerUseCase) : ViewModel() {

    val playersLiveData by lazy { MutableLiveData<List<PlayerEntity>>() }

    fun getPlayers() {
        viewModelScope.launch {
            playersLiveData.value = playerUseCase.getPlayers()
        }
    }

    fun removePlayer(id: String) {
        viewModelScope.launch {
            playerUseCase.removePlayer(id)
        }
    }
}
