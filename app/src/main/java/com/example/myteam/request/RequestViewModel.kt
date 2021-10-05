package com.example.myteam.request

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Player
import com.example.domain.entity.Position
import com.example.domain.usecase.PlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(private val playerUseCase: PlayerUseCase) : ViewModel() {

    val playersLiveData: MutableLiveData<List<Player>> by lazy { MutableLiveData<List<Player>>(emptyList()) }

    init {
        viewModelScope.launch {
            playersLiveData.value = playerUseCase.getPlayers()
        }
    }

    fun insertPlayer() {
        viewModelScope.launch {
            playerUseCase.insertPlayer("Nico", Position.GK)
            playersLiveData.value = playerUseCase.getPlayers()
        }
    }
}
