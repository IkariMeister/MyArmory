package com.jcgseco.myarmory.core.commons.navigation

import android.content.Intent
import java.io.Serializable

sealed class Screen {

    class Close(
        @Deprecated("Use values instead of Intent", ReplaceWith("values"))
        val intent: Intent? = null,
        val resultCode: Int? = null,
        val values: List<Pair<String, Serializable>>? = null
    ) : Screen()
}
