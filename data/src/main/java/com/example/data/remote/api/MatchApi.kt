package com.example.data.remote.api

import com.example.data.remote.model.response.MatchModel
import retrofit2.http.GET

interface MatchApi {

    @GET("/api/v1/available_matches")
    suspend fun listRepos(): Array<MatchModel>?
}
