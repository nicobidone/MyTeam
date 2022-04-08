package com.example.data.endpoint.mapper

import com.example.data.endpoint.model.response.MatchModel
import com.example.domain.entity.MatchEntity

fun MatchModel.toMatchEntity() = MatchEntity(
    field = this.field ?: "",
    players = this.players ?: 0,
    time = this.time ?: 0
)
