package com.jcgseco.myarmory.core.commons.eventtracker.event

interface AnalyticEvent {

    val eventName: String

    val properties: Map<String, Any>? get() = null
    val isSpecialEvent: Boolean get() = false
}
