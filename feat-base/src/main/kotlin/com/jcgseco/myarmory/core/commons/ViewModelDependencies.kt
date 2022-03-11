package com.jcgseco.myarmory.core.commons

import com.jcgseco.myarmory.core.commons.data.preferences.SessionPreferences
import com.jcgseco.myarmory.core.commons.eventtracker.EventsTracker
import com.jcgseco.myarmory.core.commons.logger.Logger
import com.jcgseco.myarmory.core.commons.navigation.Navigator
import javax.inject.Inject

class ViewModelDependencies @Inject constructor(
    val sessionPreferences: SessionPreferences,
    val logger: Logger,
    val navigatorProvider: Navigator.Provider,
    val eventsTracker: EventsTracker,
)
