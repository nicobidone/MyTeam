package com.example.myteam.team

import com.example.myteam.R
import com.example.myteam.base.BaseActivity
import com.example.myteam.databinding.ActivityTeamBinding
import com.example.myteam.utils.navigation.AnimationFlow
import com.example.myteam.utils.navigation.NavigationExitWrapper
import com.example.myteam.utils.navigation.closeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamActivity : BaseActivity<ActivityTeamBinding>() {

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

    override fun getBindingClass(): ActivityTeamBinding = ActivityTeamBinding.inflate(layoutInflater)
}
