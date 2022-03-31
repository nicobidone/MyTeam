package com.example.myteam.team

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.PlayerEntity
import com.example.myteam.base.BaseFragment
import com.example.myteam.databinding.FragmentTeamBinding
import com.example.myteam.team.TeamFragmentDirections.Companion.toCreatePlayerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamFragment : BaseFragment<FragmentTeamBinding, TeamActivity>() {

    private val viewModel: TeamViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOnClickListeners()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPlayers()
    }

    private fun initOnClickListeners() {
        binding.fabTeamAddPlayer.setOnClickListener(addPlayerListener)
    }

    private fun initObservers() {
        viewModel.playersLiveData.observe(viewLifecycleOwner, playersObserver())
    }

    private fun playersObserver() = Observer<List<PlayerEntity>> {
        setUpRecyclerView(it)
    }

    private fun setUpRecyclerView(dialogResult: List<PlayerEntity>) {
        val myAdapter = TeamPlayerAdapter(
            dialogResult.toMutableList(),
            { findNavController().navigate(toCreatePlayerFragment(it)) },
            { viewModel.removePlayer(it.id) }
        )
        binding.rvTeamPlayers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
            ItemTouchHelper(SwipeHelperCallback(myAdapter)).attachToRecyclerView(this)
        }
    }

    private val addPlayerListener = View.OnClickListener {
        findNavController().navigate(toCreatePlayerFragment())
    }

    override fun getBindingClass() = FragmentTeamBinding.inflate(layoutInflater)
}
