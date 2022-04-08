package com.example.data.endpoint.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchModel(
    @Json(name = "field") val field: String? = null,
    @Json(name = "players") val players: Int? = null,
    @Json(name = "time") val time: Int? = null,
    @Json(name = "id") val id: String? = null
)
