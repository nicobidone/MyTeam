package com.example.myteam.team

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Player
import com.example.myteam.R
import com.example.myteam.base.BaseFragment
import com.example.myteam.databinding.FragmentRequestBinding
import com.example.myteam.databinding.ItemRequestBinding
import com.example.myteam.utils.adapter.GenericAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestFragment : BaseFragment<FragmentRequestBinding, TeamActivity>() {

    private val viewModel: RequestViewModel by viewModels()
    private var isFABOpen = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOnClickListeners()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPlayers()
        isFABOpen = false
    }

    private fun initOnClickListeners() {
        binding.fabRequestMenu.setOnClickListener(fabListener)
        binding.fabRequestAddPlayer.setOnClickListener(addPlayerListener)
    }

    private fun initObservers() {
        viewModel.playersLiveData.observe(viewLifecycleOwner, playersObserver)
    }

    private val playersObserver = Observer<List<Player>> {
        createRecyclerView(it)
    }

    private fun createRecyclerView(dialogResult: List<Player>) {
        val myAdapter = object : GenericAdapter<Player>(dialogResult) {
            override fun getLayoutId(position: Int, obj: Player): Int = R.layout.item_request
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                RequestViewHolder(ItemRequestBinding.inflate(layoutInflater))
        }
        binding.rvRequestPlayers.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = myAdapter
            visibility = View.VISIBLE
        }
    }

    private val addPlayerListener = View.OnClickListener {
        findNavController().navigate(RequestFragmentDirections.actionRequestFragmentToCreatePlayerFragment())
    }

    private val fabListener = View.OnClickListener {
        if (!isFABOpen) {
            showFABMenu()
        } else {
            closeFABMenu()
        }
    }

    private fun showFABMenu() {
        with(binding) {
            isFABOpen = true
            fabRequestAddPlayer.enterAnimation(R.dimen.first_button)
            fabRequestEditPlayer.enterAnimation(R.dimen.second_button)
            fabRequestRemovePlayer.enterAnimation(R.dimen.third_button)
        }
    }

    private fun closeFABMenu() {
        isFABOpen = false
        with(binding) {
            fabRequestAddPlayer.exitAnimation()
            fabRequestEditPlayer.exitAnimation()
            fabRequestRemovePlayer.exitAnimation()
        }
    }

    override fun getBindingClass(): FragmentRequestBinding = FragmentRequestBinding.inflate(layoutInflater)

    private fun FloatingActionButton.enterAnimation(dimen: Int) {
        with(this) {
            animate().translationY(-resources.getDimension(dimen))
            animate().alpha(1f)
            elevation = 16f
        }
    }

    private fun FloatingActionButton.exitAnimation() {
        with(this) {
            animate().translationY(0f)
            animate().alpha(0f)
            elevation = 0f
        }
    }
}
