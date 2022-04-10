package com.example.data.remote.service

import com.example.data.remote.ServiceResult
import com.example.data.remote.model.response.MatchModel

interface MatchService {
    suspend fun getMatches(): ServiceResult<List<MatchModel>>
}
