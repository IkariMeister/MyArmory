package com.jcgseco.myarmory.ui.splash

import android.os.Handler
import android.os.Looper
import com.jcgseco.myarmory.core.commons.ViewModelDependencies
import com.jcgseco.myarmory.core.commons.data.preferences.GlobalPreferences
import com.jcgseco.myarmory.core.commons.eventtracker.GlobalProperties
import com.jcgseco.myarmory.core.commons.navigation.GlobalScreens
import com.jcgseco.myarmory.core.commons.ui.BaseViewModel
import com.jcgseco.myarmory.core.commons.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    viewModelDependencies: ViewModelDependencies,
    private val globalPreferences: GlobalPreferences
) : BaseViewModel(viewModelDependencies) {

    private val isFirstOpen = globalPreferences.isFirstOpen()

    protected override val viewStateFlow = MutableStateFlow<ViewState>(SplashViewState.Content)

    fun init() {
        configureEventsTracker()
        registerAppOpen()
        startRouting()
    }

    private fun configureEventsTracker() {
        eventsTracker.addGlobalProperties(GlobalProperties().apply { isAnonymousUser(true) })
    }

    private fun registerAppOpen() {
        if (isFirstOpen) eventsTracker.trackEvent(SplashEvent.FirstOpen)
        globalPreferences.registerApplicationOpen()
    }

    private fun startRouting() = executeWithDelay { navigateTo(GlobalScreens.Home) }

    private fun executeWithDelay(block: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({ block() }, SPLASH_START_DELAY_IN_MILLIS)
    }

    companion object {
        private const val SPLASH_START_DELAY_IN_MILLIS = 1000L
    }
}

sealed class SplashViewState {
    object Content: SplashViewState(), ViewState.Content
    object UnexpectedError : SplashViewState(), ViewState.UnexpectedError
}
