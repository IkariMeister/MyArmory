package com.jcgseco.myarmory.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jcgseco.myarmory.R
import com.jcgseco.myarmory.core.commons.navigation.Navigator
import com.jcgseco.myarmory.uicomponents.extensions.inTransaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        navigateToHome()
    }

    fun navigateToHome() = replaceFragment(HomeFragment())

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction { replace(R.id.container, fragment) }
    }

    companion object {

        fun launch(activity: Activity) {
            val intent = Intent(activity, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            activity.startActivity(intent)
        }
    }
}

object GoToHomeScreenNavigator : Navigator {

    override fun navigate(activity: Activity) {
        if (activity is HomeActivity) {
            activity.navigateToHome()
        } else {
            HomeActivity.launch(activity)
        }
    }
}
