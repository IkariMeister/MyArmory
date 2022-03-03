package com.jcgseco.myarmory.core.commons.actions

sealed class Action {

    class ShowMessage(val message: Message) : Action()
    class ShowDialog(val dialogConfiguration: DialogConfiguration) : Action()
    object DoNothing : Action()
}
