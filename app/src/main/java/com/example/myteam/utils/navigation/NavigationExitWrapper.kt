package com.example.myteam.utils.navigation

import android.app.Activity
import android.content.Intent

data class NavigationExitWrapper(
    var resultCode: Int = Activity.RESULT_CANCELED,
    val requestCode: Int = Activity.RESULT_CANCELED,
    var animationFlow: AnimationFlow = AnimationFlow(),
    var intent: Intent? = null
)
