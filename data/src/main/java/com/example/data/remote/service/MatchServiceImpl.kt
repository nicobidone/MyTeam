package com.example.data.remote.service

import com.example.data.remote.ServiceResult
import com.example.data.remote.api.MatchApi
import com.example.data.remote.model.response.MatchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class MatchServiceImpl @Inject constructor(private val retrofit: Retrofit) : MatchService {

    override suspend fun getMatches(): ServiceResult<List<MatchModel>> {
        val api = retrofit.create(MatchApi::class.java)
        return try {
            withContext(Dispatchers.IO) {
                ServiceResult.Success(api.listRepos()?.toList() ?: emptyList())
            }
        } catch (e: Exception) {
            ServiceResult.Error(e.toString())
        }
    }
}
