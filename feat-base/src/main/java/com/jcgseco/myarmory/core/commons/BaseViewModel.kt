package com.jcgseco.myarmory.core.commons

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.jcgseco.myarmory.core.commons.actions.Action
import com.jcgseco.myarmory.core.commons.actions.DialogConfiguration
import com.jcgseco.myarmory.core.commons.actions.Message
import com.jcgseco.myarmory.core.commons.fragment.toolbar.ViewState
import com.jcgseco.myarmory.core.commons.navigation.DoNotNavigate
import com.jcgseco.myarmory.core.commons.navigation.Navigator
import com.jcgseco.myarmory.core.commons.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel(
    private val viewModelDependencies: ViewModelDependencies
) : ViewModel(), LifecycleObserver {

    protected val logger = viewModelDependencies.logger
    protected val eventsTracker = viewModelDependencies.eventsTracker

    private val navigation: MutableStateFlow<Navigator> = MutableStateFlow(DoNotNavigate)
    val navigateTo: StateFlow<Navigator>
        get() = navigation

    private val executeAction = MutableStateFlow<Action>(Action.DoNothing)
    val action: StateFlow<Action>
        get() = executeAction
    protected abstract val stateFlow: MutableStateFlow<ViewState>
    val viewState: StateFlow<ViewState>
        get() = stateFlow

    fun navigateTo(screen: Screen) {
        navigation.value = viewModelDependencies.navigatorProvider.get(screen)
    }

    fun showMessage(message: Message) {
        executeAction.value = Action.ShowMessage(message)
    }

    fun showDialog(dialogConfiguration: DialogConfiguration) {
        executeAction.value = Action.ShowDialog(dialogConfiguration)
    }
}
