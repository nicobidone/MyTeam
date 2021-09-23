package com.example.myteam.utils.navigation

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment

fun Fragment.navigate(navigationWrapper: NavigationWrapper) {
    requireActivity().navigate(navigationWrapper)
}

fun Activity.navigate(wrapper: NavigationWrapper) {
    val intent = Intent(this, wrapper.destination).apply {
        if (wrapper.replaceFlags) flags = wrapper.flags else addFlags(wrapper.flags)
        if (wrapper.bundle != null) intent.putExtras(wrapper.bundle)
    }
    val options: ActivityOptionsCompat? =
        if (wrapper.viewPairs != null) ActivityOptionsCompat.makeSceneTransitionAnimation(this, *wrapper.viewPairs) else null
    when {
        wrapper.requestCode == null && options == null -> startActivity(intent)
        wrapper.requestCode == null && options != null -> startActivity(intent, options.toBundle())
        wrapper.requestCode != null && options == null -> startActivityForResult(intent, wrapper.requestCode)
        wrapper.requestCode != null && options != null -> startActivityForResult(intent, wrapper.requestCode, options.toBundle())
    }
    when {
        wrapper.isFinishOnExit && options == null -> finish()
        wrapper.isFinishOnExit && options != null -> shouldFinishActivity = true
    }
    overridePendingTransition(wrapper.animationFlow.intro, wrapper.animationFlow.exit)
}

private var shouldFinishActivity = false

fun Activity.closeActivity(wrapper: NavigationExitWrapper) {
    if (wrapper.intent == null) setResult(wrapper.resultCode) else setResult(wrapper.resultCode, wrapper.intent)
    finish()
    overridePendingTransition(wrapper.animationFlow.intro, wrapper.animationFlow.exit)
}
