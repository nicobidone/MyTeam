package com.example.myteam.request

import com.example.myteam.base.BaseActivity
import com.example.myteam.R
import com.example.myteam.databinding.ActivityRequestBinding
import com.example.myteam.utils.navigation.AnimationFlow
import com.example.myteam.utils.navigation.NavigationExitWrapper
import com.example.myteam.utils.navigation.closeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestActivity : BaseActivity<ActivityRequestBinding>() {

    override fun onBackPressed() {
        super.onBackPressed()
        closeActivity(
            NavigationExitWrapper(
                animationFlow = AnimationFlow(
                    intro = R.anim.slide_in_left,
                    exit = R.anim.slide_out_right
                )
            )
        )
    }

    override fun getBindingClass(): ActivityRequestBinding = ActivityRequestBinding.inflate(layoutInflater)
}
