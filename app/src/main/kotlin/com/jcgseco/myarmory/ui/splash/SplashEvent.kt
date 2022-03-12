package com.jcgseco.myarmory.ui.splash

import com.jcgseco.myarmory.core.commons.eventtracker.event.AnalyticEvent

sealed class SplashEvent : AnalyticEvent {


    object FirstOpen : SplashEvent() {
        override val eventName: String = "${EVENT_GROUP}_firstopen"
        override val isSpecialEvent: Boolean = true
    }

    companion object {
        private const val EVENT_GROUP = "splash"
    }
}

