package com.example.domain.usecase

import com.example.domain.entity.MatchEntity
import com.example.domain.repository.MatchRepository
import javax.inject.Inject

class MatchUseCase @Inject constructor(private val matchRepository: MatchRepository) {

    suspend fun getMatches(): List<MatchEntity> = matchRepository.getMatches()
}
