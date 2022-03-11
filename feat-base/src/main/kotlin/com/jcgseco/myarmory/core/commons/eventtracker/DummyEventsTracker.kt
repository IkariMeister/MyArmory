package com.jcgseco.myarmory.core.commons.eventtracker

import com.jcgseco.myarmory.core.commons.eventtracker.event.AnalyticEvent

class DummyEventsTracker : EventsTracker {

    override fun trackEvent(event: AnalyticEvent) {
        // Do nothing
    }

    override fun addGlobalProperties(properties: GlobalProperties) {
        // Do nothing
    }

    override fun clear() {
        // Do nothing
    }
}
