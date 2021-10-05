package com.example.myteam.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.domain.entity.Player
import com.example.myteam.databinding.FragmentRequestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestFragment : Fragment() {

    private val viewModel: RequestViewModel by viewModels()
    private lateinit var binding: FragmentRequestBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRequestBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener { viewModel.insertPlayer() }
        viewModel.playersLiveData.observe(viewLifecycleOwner, observer)
    }

    private val observer = Observer<List<Player>> {
        binding.text.text = it.size.toString()
    }
}
