package com.example.myteam.request

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.domain.entity.Player
import com.example.myteam.BaseFragment
import com.example.myteam.databinding.FragmentRequestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestFragment : BaseFragment<FragmentRequestBinding, RequestActivity>() {

    private val viewModel: RequestViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener { viewModel.insertPlayer() }
        viewModel.playersLiveData.observe(viewLifecycleOwner, observer)
    }

    private val observer = Observer<List<Player>> {
        binding.text.text = it.size.toString()
    }

    override fun getBindingClass(): FragmentRequestBinding = FragmentRequestBinding.inflate(layoutInflater)
}
