package com.example.data

import com.example.domain.entity.Player
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

    override suspend fun getPlayers(): List<Player> {
        val result = mutableListOf<Player>()
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
}

fun PlayerRealm.mapPlayer() = Player(
    name = name,
    age = age,
    position = position.cleanStringList().map { it.toPosition() }
)
