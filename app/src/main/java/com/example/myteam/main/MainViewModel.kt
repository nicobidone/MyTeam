package com.example.myteam.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MatchEntity
import com.example.domain.usecase.MatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val matchUseCase: MatchUseCase) : ViewModel() {

    val matchesLiveData by lazy { MutableLiveData<List<MatchEntity>>() }

    fun getMatches() {
        viewModelScope.launch {
            matchesLiveData.value = matchUseCase.getMatches()
        }
    }
}
