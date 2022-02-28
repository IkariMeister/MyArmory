package com.jcgseco.myarmory.core.commons.navigation

import android.app.Activity

interface Navigator {

    fun navigate(activity: Activity)

    interface Provider {

        fun get(screen: Screen): Navigator
    }
}

object DoNotNavigate : Navigator {
    override fun navigate(activity: Activity) {
        // Do Nothing
    }

}
