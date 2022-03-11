package com.jcgseco.myarmory.core.commons.eventtracker

import com.jcgseco.myarmory.core.commons.eventtracker.event.AnalyticEvent

interface EventsTracker {

    fun trackEvent(event: AnalyticEvent)

    fun addGlobalProperties(properties: GlobalProperties)

    fun clear()
}
