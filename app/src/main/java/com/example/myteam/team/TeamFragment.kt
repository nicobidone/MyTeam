package com.example.myteam.team

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.PlayerEntity
import com.example.myteam.base.BaseFragment
import com.example.myteam.databinding.FragmentTeamBinding
import com.example.myteam.team.TeamFragmentDirections.Companion.toCreatePlayerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamFragment : BaseFragment<FragmentTeamBinding, TeamActivity>(), OnStartDragListener {

    private val viewModel: TeamViewModel by viewModels()
    private var itemTouchHelper: ItemTouchHelper? = null
    private lateinit var myAdapter: TeamPlayerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
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

    private val addPlayerListener = View.OnClickListener {
        findNavController().navigate(toCreatePlayerFragment())
    }

    private fun initObservers() {
        viewModel.playersLiveData.observe(viewLifecycleOwner, playersObserver())
    }

    private fun playersObserver() = Observer<List<PlayerEntity>> {
        myAdapter.setData(it)
    }

    private fun setUpRecyclerView() {
        myAdapter = TeamPlayerAdapter(
            this,
            { viewModel.updateList(it) },
            { findNavController().navigate(toCreatePlayerFragment(it)) },
            { viewModel.removePlayer(it.id) }
        )
        binding.rvTeamPlayers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
            itemTouchHelper = ItemTouchHelper(HelperCallback(myAdapter))
            itemTouchHelper?.attachToRecyclerView(this)
        }
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let { itemTouchHelper?.startDrag(it) }
    }

    override fun getBindingClass() = FragmentTeamBinding.inflate(layoutInflater)
}
