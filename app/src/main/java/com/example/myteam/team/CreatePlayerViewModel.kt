package com.example.myteam.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Position
import com.example.domain.usecase.PlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePlayerViewModel @Inject constructor(private val playerUseCase: PlayerUseCase) : ViewModel() {

    fun insertPlayer(name: String, age: Int, position: List<Position>) {
        viewModelScope.launch {
            playerUseCase.insertPlayer(name, age, position)
        }
    }
}
