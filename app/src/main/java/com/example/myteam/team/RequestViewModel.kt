package com.example.myteam.team

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Player
import com.example.domain.usecase.PlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(private val playerUseCase: PlayerUseCase) : ViewModel() {

    val playersLiveData: MutableLiveData<List<Player>> by lazy { MutableLiveData<List<Player>>(emptyList()) }

    fun getPlayers() {
        viewModelScope.launch {
            playersLiveData.value = playerUseCase.getPlayers()
        }
    }
}
