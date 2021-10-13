package com.example.myteam.main

import androidx.navigation.Navigation
import com.example.myteam.R
import com.example.myteam.base.BaseActivity
import com.example.myteam.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getBinding() = ActivityMainBinding.inflate(layoutInflater)
    override fun getNavController() = Navigation.findNavController(this, R.id.nhf_activity_main)
}
