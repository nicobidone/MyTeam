package com.example.data.remote.mapper

import com.example.data.remote.model.response.MatchModel
import com.example.domain.entity.MatchEntity

internal fun MatchModel.toMatchEntity() = MatchEntity(
    field = this.field ?: "",
    players = this.players ?: 0,
    time = this.time?.toLong() ?: 0L
)
