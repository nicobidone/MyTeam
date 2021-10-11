package com.example.domain.usecase

import com.example.domain.entity.Position
import com.example.domain.repository.PlayerRepository
import javax.inject.Inject

class PlayerUseCase @Inject constructor(private val playerRepository: PlayerRepository) {

    suspend fun insertPlayer(name: String, age: Int, positions: List<Position>) {
        playerRepository.setPlayer(name, age, positions)
    }

    suspend fun getPlayers() = playerRepository.getPlayers()
}
