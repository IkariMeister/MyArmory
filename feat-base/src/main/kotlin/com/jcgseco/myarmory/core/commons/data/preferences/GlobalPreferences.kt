package com.jcgseco.myarmory.core.commons.data.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GlobalPreferences @Inject constructor(
    @ApplicationContext context: Context
) {

    private val sharedPreferences = context.getSharedPreferences("GLOBAL_SHARED_PREF", Context.MODE_PRIVATE)

    fun isFirstOpen(): Boolean = sharedPreferences.getInt(APPLICATION_OPENS_KEY, 0) == 0

    fun registerApplicationOpen() {
        val opens = sharedPreferences.getInt(APPLICATION_OPENS_KEY, 0) + 1
        sharedPreferences.edit().putInt(APPLICATION_OPENS_KEY, opens).apply()
    }

    companion object {

        const val APPLICATION_OPENS_KEY = "APPLICATION_OPENS_KEY"
    }
}
