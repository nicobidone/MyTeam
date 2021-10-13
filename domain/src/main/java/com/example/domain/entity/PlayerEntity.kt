package com.example.domain.entity

import java.io.Serializable

data class PlayerEntity(
    val id: String,
    val name: String,
    val age: Int,
    val position: List<Position>
) : Serializable
