package com.example.myteam.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.domain.entity.MatchEntity
import com.example.myteam.R
import com.example.myteam.base.BaseFragment
import com.example.myteam.databinding.FragmentMainBinding
import com.example.myteam.team.TeamActivity
import com.example.myteam.utils.navigation.AnimationFlow
import com.example.myteam.utils.navigation.NavigationWrapper
import com.example.myteam.utils.navigation.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainActivity>() {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpInit()
        setUpOnClickListeners()
        setUpObservers()
    }

    private fun setUpInit() {
        viewModel.getMatches()
    }

    private fun setUpObservers() {
        viewModel.matchesLiveData.observe(viewLifecycleOwner, matchesObserver())
    }

    private fun setUpOnClickListeners() {
        binding.btnMainRequest.setOnClickListener(requestListener)
    }

    private fun matchesObserver() = Observer<List<MatchEntity>> {
        binding.tvMainRequest.text = it.toString()
    }

    private val requestListener = View.OnClickListener {
        navigate(
            NavigationWrapper(
                animationFlow = AnimationFlow(
                    intro = R.anim.slide_in_right,
                    exit = R.anim.slide_out_left
                ),
                destination = TeamActivity::class.java,
            )
        )
    }

    override fun getBindingClass() = FragmentMainBinding.inflate(layoutInflater)
}
