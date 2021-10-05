package com.example.data

import com.example.domain.entity.Player
import com.example.domain.entity.Position
import com.example.domain.entity.toPosition
import com.example.domain.repository.PlayerRepository
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(private val realm: Realm) : PlayerRepository {

    override suspend fun setPlayer(name: String, position: Position) {
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            realmTransaction.insert(PlayerRealm(name = name, position = position.toString()))
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
    position = position.toPosition()
)
