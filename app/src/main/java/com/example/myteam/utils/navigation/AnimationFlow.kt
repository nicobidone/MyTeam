package com.example.myteam.utils.navigation

import android.os.Parcelable
import androidx.annotation.AnimRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimationFlow(
    @AnimRes var intro: Int = 0,
    @AnimRes var exit: Int = 0
) : Parcelable
