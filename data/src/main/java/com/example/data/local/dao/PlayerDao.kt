package com.example.data.local.dao

import com.example.domain.entity.PlayerEntity
import com.example.domain.entity.Position

interface PlayerDao {

    suspend fun setPlayer(name: String, age: Int, positions: List<Position>)
    suspend fun getPlayers(): List<PlayerEntity>
    suspend fun updatePlayer(id: String, name: String, age: Int, position: List<Position>)
    suspend fun removePlayer(id: String)
    suspend fun updateList(list: List<PlayerEntity>)
}
