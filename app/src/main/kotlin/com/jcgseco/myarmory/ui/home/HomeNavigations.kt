package com.jcgseco.myarmory.ui.home

import android.app.Activity
import android.content.Intent
import com.jcgseco.myarmory.core.commons.navigation.Navigator
import com.jcgseco.myarmory.ui.splash.SplashActivity
import java.io.Serializable

sealed class HomeNavigations : Navigator

class CloseScreen(
    private val resultCode: Int?,
    val values: List<Pair<String, Serializable>>?
) : HomeNavigations() {

    override fun navigate(activity: Activity) {
        if (activity.callingActivity?.className == SplashActivity::class.java.name) {
            GoToHomeScreenNavigator.navigate(activity)
        } else {
            values?.let { params ->
                val newIntent = Intent().apply { params.map { putExtra(it.first, it.second) } }
                activity.setResult(resultCode!!, newIntent)
            }
            activity.finish()
        }
    }
}

object FakeNavigation: HomeNavigations() {

    override fun navigate(activity: Activity) { /* Do nothing */}
}


