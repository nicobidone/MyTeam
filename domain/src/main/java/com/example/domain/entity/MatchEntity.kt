package com.example.domain.entity

import java.io.Serializable

data class MatchEntity(
    val field: String,
    val players: Int,
    val time: Long
) : Serializable
