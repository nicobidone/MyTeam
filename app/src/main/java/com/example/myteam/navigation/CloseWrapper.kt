package com.prisma.todopago.commons.ui.utils.navigation

import android.app.Activity
import android.content.Intent
import com.example.myteam.navigation.AnimationFlow

data class CloseWrapper(
    var resultCode: Int = Activity.RESULT_CANCELED,
    val requestCode: Int = Activity.RESULT_CANCELED,
    var animationFlow: AnimationFlow = AnimationFlow(),
    var intent: Intent? = null
)
