package com.jcgseco.myarmory.core.commons.logger

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.jcgseco.myarmory.core.commons.data.preferences.SessionPreferences

class CrashlyticsLogger(sessionPreferences: SessionPreferences) : Logger {

    private var crashlytics: FirebaseCrashlytics = FirebaseCrashlytics.getInstance()

    override fun debug(tag: String, message: String) {
        crashlytics.log("DEBUG: $message")
    }

    override fun info(tag: String, message: String) {
        crashlytics.log("INFO: $message")
    }

    override fun error(tag: String, message: String, error: Throwable) {
        crashlytics.log("ERROR: $message")
        crashlytics.recordException(error)
    }

    private fun clearTemporalDimens() {
        crashlytics.setCustomKey("HTTP_ERROR_CODE", "-")
        crashlytics.setCustomKey("ENDPOINT", "-")
        crashlytics.setCustomKey("METHOD", "-")
    }
}
