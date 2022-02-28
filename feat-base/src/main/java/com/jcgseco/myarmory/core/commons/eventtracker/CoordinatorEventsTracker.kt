package com.jcgseco.myarmory.core.commons.eventtracker

import com.jcgseco.myarmory.core.commons.eventtracker.event.AnalyticEvent

class CoordinatorEventsTracker(
    private val trackers: List<EventsTracker>
) : EventsTracker {

    override fun trackEvent(event: AnalyticEvent) {
        trackers.forEach { it.trackEvent(event) }
    }

    override fun addGlobalProperties(properties: GlobalProperties) {
        trackers.forEach { it.addGlobalProperties(properties) }
    }

    override fun clear() {
        trackers.forEach { it.clear() }
    }
}
