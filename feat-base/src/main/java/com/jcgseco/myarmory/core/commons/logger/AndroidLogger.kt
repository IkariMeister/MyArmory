package com.jcgseco.myarmory.core.commons.logger

import android.util.Log

class AndroidLogger : Logger {

    override fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun info(tag: String, message: String) {
        Log.i(tag, message)
    }

    override fun error(tag: String, message: String, error: Throwable) {
        Log.e(tag, message, error)
    }
}
