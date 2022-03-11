package com.jcgseco.myarmory.ui.home

import com.jcgseco.myarmory.core.commons.navigation.GlobalScreens
import com.jcgseco.myarmory.core.commons.navigation.Navigator
import com.jcgseco.myarmory.core.commons.navigation.Screen

object HomeNavigationProvider : Navigator.Provider {
    override fun get(screen: Screen): Navigator =
        when(screen) {
            is GlobalScreens.Close -> CloseScreen(screen.resultCode, screen.values)
            GlobalScreens.Home -> GoToHomeScreenNavigator
            GlobalScreens.Stay -> FakeNavigation
        }
}


