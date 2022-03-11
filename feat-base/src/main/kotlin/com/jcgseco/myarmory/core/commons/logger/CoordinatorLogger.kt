package com.jcgseco.myarmory.core.commons.logger

import com.jcgseco.myarmory.core.commons.data.preferences.SessionPreferences
import javax.inject.Inject

class CoordinatorLogger @Inject constructor(
    sessionPreferences: SessionPreferences,
    private val loggers: List<Logger>,
) : Logger {



    override fun debug(tag: String, message: String) {
        loggers.forEach { it.debug(tag, message) }
    }

    override fun info(tag: String, message: String) {
        loggers.forEach { it.info(tag, message) }
    }

    override fun error(tag: String, message: String, error: Throwable) {
        loggers.forEach { it.error(tag, message, error) }
    }
}
