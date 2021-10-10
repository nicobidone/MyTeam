package com.example.myteam.team

import androidx.fragment.app.viewModels
import com.example.myteam.base.BaseFragment
import com.example.myteam.databinding.FragmentCreatePlayerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePlayerFragment : BaseFragment<FragmentCreatePlayerBinding, TeamActivity>() {

    private val viewModel: CreatePlayerViewModel by viewModels()

    override fun getBindingClass(): FragmentCreatePlayerBinding = FragmentCreatePlayerBinding.inflate(layoutInflater)
}
