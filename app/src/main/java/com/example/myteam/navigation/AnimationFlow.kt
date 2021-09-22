package com.example.myteam.navigation

import android.os.Parcelable
import androidx.annotation.AnimRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimationFlow(
    @AnimRes var introAnimation: Int = 0,
    @AnimRes var exitAnimation: Int = 0
) : Parcelable
