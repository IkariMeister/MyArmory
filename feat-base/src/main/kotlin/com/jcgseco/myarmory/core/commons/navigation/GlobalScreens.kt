package com.jcgseco.myarmory.core.commons.navigation

import java.io.Serializable

sealed class GlobalScreens : Screen {

    class Close(
        val resultCode: Int? = null,
        val values: List<Pair<String, Serializable>>? = null
    ) : GlobalScreens()

    object Stay : GlobalScreens()

    object Home : GlobalScreens()
}
