package com.example.domain.usecase

import com.example.domain.entity.PlayerEntity
import com.example.domain.entity.Position
import com.example.domain.repository.PlayerRepository
import javax.inject.Inject

class PlayerUseCase @Inject constructor(private val playerRepository: PlayerRepository) {

    private suspend fun insertPlayer(name: String, age: Int, positions: List<Position>) {
        playerRepository.setPlayer(name, age, positions)
    }

    private suspend fun updatePlayer(id: String, name: String, age: Int, position: List<Position>) {
        playerRepository.updatePlayer(id, name, age, position)
    }

    suspend fun getPlayers() = playerRepository.getPlayers()

    suspend fun removePlayer(id: String) {
        playerRepository.removePlayer(id)
    }

    fun isValidData(player: PlayerEntity): Boolean = with(player) { name.isNotEmpty() && age > 0 && position.isNotEmpty() }

    suspend fun updateData(player: PlayerEntity) = with(player) {
        if (id.isEmpty()) {
            insertPlayer(name, age, position)
        } else {
            updatePlayer(id, name, age, position)
        }
    }

    suspend fun updateList(list: List<PlayerEntity>) {
        playerRepository.updateList(list)
    }
}
