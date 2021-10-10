package com.example.myteam.team

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.Player
import com.example.myteam.base.BaseFragment
import com.example.myteam.compound.view.StatusCompoundView
import com.example.myteam.databinding.FragmentRequestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestFragment : BaseFragment<FragmentRequestBinding, TeamActivity>() {

    private val viewModel: RequestViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.playersLiveData.observe(viewLifecycleOwner, observer)

        binding.statusView.setStatus(StatusCompoundView.Status.SUCCESS)

        binding.button.setOnClickListener {
            findNavController().navigate(RequestFragmentDirections.actionRequestFragmentToCreatePlayerFragment())
        }
    }

    private val observer = Observer<List<Player>> {
        binding.text.text = it.size.toString()
    }

    override fun getBindingClass(): FragmentRequestBinding = FragmentRequestBinding.inflate(layoutInflater)
}
