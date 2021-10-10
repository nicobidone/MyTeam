package com.example.myteam.team

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.domain.entity.Player
import com.example.myteam.base.BaseFragment
import com.example.myteam.compound.view.StatusCompoundView
import com.example.myteam.databinding.FragmentRequestBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RequestFragment : BaseFragment<FragmentRequestBinding, TeamActivity>() {

    private val viewModel: RequestViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener { viewModel.insertPlayer() }
        viewModel.playersLiveData.observe(viewLifecycleOwner, observer)

        GlobalScope.launch(context = Dispatchers.Main) {
            showLoader()
            delay(5000)
            hideLoader()
        }

        binding.statusView.setStatus(StatusCompoundView.Status.SUCCESS)
    }

    private val observer = Observer<List<Player>> {
        binding.text.text = it.size.toString()
    }

    override fun getBindingClass(): FragmentRequestBinding = FragmentRequestBinding.inflate(layoutInflater)
}
