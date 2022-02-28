package com.jcgseco.myarmory.core.commons.eventtracker

import android.app.Application
import android.os.Bundle
import com.google.android.gms.tasks.Tasks
import com.google.firebase.analytics.FirebaseAnalytics
import com.jcgseco.myarmory.core.commons.eventtracker.event.AnalyticEvent
import com.jcgseco.myarmory.core.commons.logger.Logger
import java.util.Locale
import javax.inject.Inject

class FirebaseEventsTracker @Inject constructor(
    private val application: Application,
    private val logger: Logger
) : EventsTracker {

    val instanceId: String get() = Tasks.await(FirebaseAnalytics.getInstance(application).appInstanceId)

    override fun trackEvent(event: AnalyticEvent) {
        val bundle = Bundle().apply {
            event.properties?.map {
                val formattedProperty = it.key.replace(" ", "_").lowercase(Locale.ROOT)
                putString(formattedProperty, it.value.toString())
            }
        }
        val formattedEventName = event.eventName.replace(" ", "_").replace(":", "_").lowercase(Locale.ROOT)
        try {
            FirebaseAnalytics.getInstance(application).logEvent(formattedEventName, bundle)
        } catch (e: Exception) {
            logger.error("ERROR:", "Firebase Analytics unexpected error", e)
        }
    }

    override fun addGlobalProperties(properties: GlobalProperties) {
        properties.properties.forEach {
            val formattedUserProperty = it.key.replace(" ", "_").lowercase(Locale.ROOT)
            FirebaseAnalytics.getInstance(application).setUserProperty(formattedUserProperty, it.value)
        }
    }

    override fun clear() {
        FirebaseAnalytics.getInstance(application).resetAnalyticsData()
    }
}
