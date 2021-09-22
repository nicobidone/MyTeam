package com.prisma.todopago.commons.ui.utils.navigation

import android.os.Bundle
import android.view.View
import androidx.core.util.Pair
import com.example.myteam.navigation.AnimationFlow

data class NavigationWrapper(
    val destination: Class<*>,
    var requestCode: Int? = null,
    var animationFlow: AnimationFlow = AnimationFlow(),
    var viewPairs: Array<Pair<View, String>>? = null,
    var flags: Int = 0,
    var replaceFlags: Boolean = false,
    var bundle: Bundle? = null,
    var isFinishOnExit: Boolean = false
)
