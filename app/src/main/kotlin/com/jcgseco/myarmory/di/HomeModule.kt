package com.jcgseco.myarmory.di

import com.jcgseco.myarmory.core.commons.eventtracker.DummyEventsTracker
import com.jcgseco.myarmory.core.commons.eventtracker.EventsTracker
import com.jcgseco.myarmory.core.commons.logger.AndroidLogger
import com.jcgseco.myarmory.core.commons.logger.Logger
import com.jcgseco.myarmory.core.commons.messages.MessageDisplayer
import com.jcgseco.myarmory.core.commons.messages.Toast
import com.jcgseco.myarmory.core.commons.navigation.Navigator
import com.jcgseco.myarmory.ui.home.HomeNavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class HomeModule {

    @Provides
    fun providesNavigatorProvider(): Navigator.Provider = HomeNavigationProvider

    @Provides
    fun providesLogger(): Logger = AndroidLogger

    @Provides
    fun providesEventTracker(): EventsTracker = DummyEventsTracker

    @Provides
    fun providesMessageDisplayer(): MessageDisplayer = Toast

}
