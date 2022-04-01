package com.example.myteam.team

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.entity.PlayerEntity
import com.example.myteam.R
import com.example.myteam.base.BaseFragment
import com.example.myteam.databinding.FragmentCreateEditPlayerBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateEditPlayerFragment : BaseFragment<FragmentCreateEditPlayerBinding, TeamActivity>() {

    private val viewModelEdit: CreateEditPlayerViewModel by viewModels()
    private val args by navArgs<CreateEditPlayerFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initOnClickListeners()
        initObservers()
    }

    private fun init() {
        args.arg?.let {
            viewModelEdit.initState(it)
            binding.btnCreatePlayer.text = getString(R.string.create_player_action_edit)
        }
    }

    private fun initObservers() {
        viewModelEdit.missingInfoLiveData.observe(viewLifecycleOwner, infoObserver())
        viewModelEdit.playerEntity.observe(viewLifecycleOwner, playerObserver())
    }

    private fun playerObserver() = Observer<PlayerEntity> {
        binding.tfCreatePlayerAge.editText?.setText(it.age.toString())
        binding.tfCreatePlayerName.editText?.setText(it.name)
        binding.psCreatePlayer.setSelected(it.position)
    }

    private fun infoObserver() = Observer<Boolean> { hasInfoComplete ->
        hasInfoComplete?.let {
            if (hasInfoComplete == true) {
                findNavController().popBackStack()
            } else {
                Snackbar.make(binding.btnCreatePlayer, R.string.create_player_missing_info, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun initOnClickListeners() {
        binding.btnCreatePlayer.setOnClickListener(createUpdatePlayerListener)
    }

    private val createUpdatePlayerListener = View.OnClickListener {
        viewModelEdit.updateData(
            binding.tfCreatePlayerName.editText?.text,
            binding.tfCreatePlayerAge.editText?.text,
            binding.psCreatePlayer.getSelectedList()
        )
    }

    override fun onPause() {
        super.onPause()
        viewModelEdit.saveState(
            binding.tfCreatePlayerName.editText?.text,
            binding.tfCreatePlayerAge.editText?.text,
            binding.psCreatePlayer.getSelectedList()
        )
    }

    override fun getBindingClass() = FragmentCreateEditPlayerBinding.inflate(layoutInflater)
}
