package com.example.data.endpoint.service

import com.example.data.endpoint.model.response.MatchModel

interface MatchService {
    suspend fun getMatches(): ServiceResult<List<MatchModel>>
}
