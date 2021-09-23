package com.example.myteam.request

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myteam.R
import com.example.myteam.databinding.ActivityRequestBinding
import com.example.myteam.utils.navigation.AnimationFlow
import com.example.myteam.utils.navigation.NavigationExitWrapper
import com.example.myteam.utils.navigation.closeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

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
}