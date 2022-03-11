package com.jcgseco.myarmory.core.commons.logger

interface Logger {

    fun debug(tag: String, message: String)
    fun info(tag: String, message: String)
    fun error(tag: String, message: String, error: Throwable)
}
