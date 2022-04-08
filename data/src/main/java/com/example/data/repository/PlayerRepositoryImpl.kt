package com.example.data.repository

import com.example.data.database.PlayerRealm
import com.example.domain.entity.PlayerEntity
import com.example.domain.entity.Position
import com.example.domain.entity.toPosition
import com.example.domain.extension.cleanStringList
import com.example.domain.repository.PlayerRepository
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(private val realm: Realm) : PlayerRepository {

    override suspend fun setPlayer(name: String, age: Int, positions: List<Position>) {
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            realmTransaction.insert(PlayerRealm(name = name, age = age, position = positions.toString()))
        }
    }

    override suspend fun getPlayers(): List<PlayerEntity> {
        val result = mutableListOf<PlayerEntity>()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            result.addAll(
                realmTransaction
                    .where(PlayerRealm::class.java)
                    .findAll()
                    .map { it.mapPlayer() }
            )
        }
        return result
    }

    override suspend fun updatePlayer(id: String, name: String, age: Int, position: List<Position>) {
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            realmTransaction
                .where(PlayerRealm::class.java)
                .equalTo("id", id)
                .findFirst()
                .apply {
                    this?.name = name
                    this?.age = age
                    this?.position = position.toString()
                }
        }
    }

    override suspend fun removePlayer(id: String) {
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            realmTransaction
                .where(PlayerRealm::class.java)
                .equalTo("id", id)
                .findFirst()
                .apply {
                    this?.deleteFromRealm()
                }
        }
    }

    override suspend fun updateList(list: List<PlayerEntity>) {
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val players = realmTransaction.where(PlayerRealm::class.java).findAll().createSnapshot()
            for (i in players.indices) {
                players[i]?.age = list[i].age
                players[i]?.name = list[i].name
                players[i]?.position = list[i].position.toString()
            }
        }
    }

    private fun PlayerRealm.mapPlayer() = PlayerEntity(
        id = id,
        name = name,
        age = age,
        position = position.cleanStringList().map { it.toPosition() }
    )
}
