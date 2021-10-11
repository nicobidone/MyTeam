package com.example.myteam.team

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myteam.base.BaseFragment
import com.example.myteam.databinding.FragmentCreatePlayerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePlayerFragment : BaseFragment<FragmentCreatePlayerBinding, TeamActivity>() {

    private val viewModel: CreatePlayerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        binding.btnCreatePlayer.setOnClickListener(createPlayerListener)
    }

    private val createPlayerListener = View.OnClickListener {
        viewModel.insertPlayer(
            binding.tfCreatePlayerName.editText?.text.toString(),
            binding.tfCreatePlayerAge.editText?.text.toString().toInt(),
            binding.psCreatePlayer.getSelectedList()
        )
        findNavController().popBackStack()
    }

    override fun getBindingClass(): FragmentCreatePlayerBinding = FragmentCreatePlayerBinding.inflate(layoutInflater)
}
