package com.example.domain.repository

import com.example.domain.entity.MatchEntity

interface MatchRepository {

    suspend fun getMatches(): List<MatchEntity>
}
