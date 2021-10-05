package com.example.domain.repository

import com.example.domain.entity.Player
import com.example.domain.entity.Position

interface PlayerRepository {
    suspend fun setPlayer(name: String, position: Position)
    suspend fun getPlayers(): List<Player>
}
