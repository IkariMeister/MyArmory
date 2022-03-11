package com.jcgseco.myarmory.core.commons.data.preferences

import android.annotation.SuppressLint
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

open class SessionPreferences @Inject constructor(
    @ApplicationContext context: Context
) {

    private val sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    @SuppressLint("ApplySharedPref")
    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        const val PREFERENCES = "SESSION_SHARED_PREF"
    }
}
