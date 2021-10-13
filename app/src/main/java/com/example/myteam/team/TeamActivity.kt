package com.example.myteam.team

import androidx.navigation.Navigation
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
        with(getNavController()) {
            when (this.currentDestination?.id) {
                R.id.createPlayerFragment -> this.popBackStack()
                else -> closeActivity()
            }
        }
    }

    private fun closeActivity() {
        closeActivity(
            NavigationExitWrapper(
                animationFlow = AnimationFlow(
                    intro = R.anim.slide_in_left,
                    exit = R.anim.slide_out_right
                )
            )
        )
    }

    override fun getNavController() = Navigation.findNavController(this, R.id.nhf_activity_team)

    override fun getBinding() = ActivityTeamBinding.inflate(layoutInflater)
}
