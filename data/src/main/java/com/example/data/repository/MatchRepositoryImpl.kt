package com.example.data.repository

import com.example.data.endpoint.mapper.toMatchEntity
import com.example.data.endpoint.service.ServiceResult
import com.example.data.endpoint.service.MatchService
import com.example.domain.entity.MatchEntity
import com.example.domain.repository.MatchRepository
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(private val matchService: MatchService) : MatchRepository {

    override suspend fun getMatches(): List<MatchEntity> =
        with(matchService.getMatches()) {
            when (this) {
                is ServiceResult.Success -> data.map { it.toMatchEntity() }
                is ServiceResult.Error -> emptyList()
            }
        }
}
