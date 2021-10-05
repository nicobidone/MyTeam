package com.example.myteam.main

import com.example.myteam.base.BaseActivity
import com.example.myteam.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getBindingClass(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}
