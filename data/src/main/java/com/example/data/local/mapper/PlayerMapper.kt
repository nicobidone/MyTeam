package com.example.data.local.mapper

import com.example.data.local.database.PlayerRealm
import com.example.domain.entity.PlayerEntity
import com.example.domain.entity.toPosition
import com.example.domain.extension.cleanStringList

internal fun PlayerRealm.mapPlayer() = PlayerEntity(
    id = id,
    name = name,
    age = age,
    position = position.cleanStringList().map { it.toPosition() }
)
