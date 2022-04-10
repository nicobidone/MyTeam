package com.example.data.repository

import com.example.data.local.dao.PlayerDao
import com.example.domain.entity.PlayerEntity
import com.example.domain.entity.Position
import com.example.domain.repository.PlayerRepository
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(private val playerDao: PlayerDao) : PlayerRepository {

    override suspend fun setPlayer(name: String, age: Int, positions: List<Position>) {
        playerDao.setPlayer(name, age, positions)
    }

    override suspend fun getPlayers(): List<PlayerEntity> {
        return playerDao.getPlayers()
    }

    override suspend fun updatePlayer(id: String, name: String, age: Int, position: List<Position>) {
        playerDao.updatePlayer(id, name, age, position)
    }

    override suspend fun removePlayer(id: String) {
        playerDao.removePlayer(id)
    }

    override suspend fun updateList(list: List<PlayerEntity>) {
        playerDao.updateList(list)
    }
}
