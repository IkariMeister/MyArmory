package com.jcgseco.myarmory.core.commons

import com.jcgseco.myarmory.core.commons.dialogs.DialogConfiguration
import com.jcgseco.myarmory.core.commons.messages.Message

sealed class Action

class ShowMessage(val message: Message) : Action()
class ShowDialog(val dialogConfiguration: DialogConfiguration) : Action()
object DoNothing : Action()
