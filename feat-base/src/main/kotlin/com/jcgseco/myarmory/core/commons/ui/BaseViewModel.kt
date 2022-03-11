package com.jcgseco.myarmory.core.commons.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.jcgseco.myarmory.core.commons.Action
import com.jcgseco.myarmory.core.commons.DoNothing
import com.jcgseco.myarmory.core.commons.ShowDialog
import com.jcgseco.myarmory.core.commons.ShowMessage
import com.jcgseco.myarmory.core.commons.ViewModelDependencies
import com.jcgseco.myarmory.core.commons.dialogs.DialogConfiguration
import com.jcgseco.myarmory.core.commons.messages.Message
import com.jcgseco.myarmory.core.commons.navigation.Navigator
import com.jcgseco.myarmory.core.commons.navigation.GlobalScreens
import com.jcgseco.myarmory.core.commons.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel(private val viewModelDependencies: ViewModelDependencies) : ViewModel(), LifecycleObserver {


    protected val sessionPreferences = viewModelDependencies.sessionPreferences
    private val navigatorProvider = viewModelDependencies.navigatorProvider
    protected val logger = viewModelDependencies.logger
    protected val eventsTracker = viewModelDependencies.eventsTracker

    private val navigationFlow = MutableStateFlow(navigatorProvider.get(GlobalScreens.Stay))
    val navigateTo: StateFlow<Navigator>
        get() = navigationFlow
    private val actionFlow = MutableStateFlow<Action>(DoNothing)
    val action: StateFlow<Action>
        get() = actionFlow
    protected abstract val viewStateFlow: MutableStateFlow<ViewState>
    val viewState: StateFlow<ViewState>
        get() = viewStateFlow

    fun navigateTo(screen: Screen) {
        navigationFlow.value = navigatorProvider.get(screen)
    }

    fun showMessage(message: Message) {
        actionFlow.value = ShowMessage(message)
    }

    fun showDialog(dialog: DialogConfiguration) {
        actionFlow.value = ShowDialog(dialog)
    }

}

sealed interface ViewState {

    interface Loading : ViewState
    interface Content : ViewState
    interface NoNetwork : ViewState
    interface UnexpectedError : ViewState
    interface NoContent : ViewState
}
