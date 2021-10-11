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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestFragment : BaseFragment<FragmentRequestBinding, TeamActivity>() {

    private val viewModel: RequestViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.playersLiveData.observe(viewLifecycleOwner, observer)
        binding.fabRequestAddPlayer.setOnClickListener(addPlayerListener)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPlayers()
    }

    private val observer = Observer<List<Player>> {
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

    override fun getBindingClass(): FragmentRequestBinding = FragmentRequestBinding.inflate(layoutInflater)
}
