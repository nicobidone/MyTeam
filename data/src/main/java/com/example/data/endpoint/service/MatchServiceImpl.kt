package com.example.data.endpoint.service

import com.example.data.endpoint.api.MatchApi
import com.example.data.endpoint.model.response.MatchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchServiceImpl @Inject constructor(private val matchApi: MatchApi) : MatchService {

    override suspend fun getMatches(): ServiceResult<List<MatchModel>> {
        return try {
            withContext(Dispatchers.IO) {
                ServiceResult.Success(matchApi.listRepos()?.toList() ?: emptyList())
            }
        } catch (e: Exception) {
            ServiceResult.Error(e.toString())
        }
    }
}
