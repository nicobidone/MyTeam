package com.example.myteam.utils.navigation

import android.os.Bundle
import android.view.View
import androidx.core.util.Pair

data class NavigationWrapper(
    val destination: Class<*>,
    val requestCode: Int? = null,
    val animationFlow: AnimationFlow = AnimationFlow(),
    val viewPairs: Array<Pair<View, String>>? = null,
    val flags: Int = 0,
    val replaceFlags: Boolean = false,
    val bundle: Bundle? = null,
    val isFinishOnExit: Boolean = false
)
