package com.jcgseco.myarmory.core.commons.messages

sealed class Message(val message: String) {

    class Info(message: String) : Message(message)
    class Warning(message: String) : Message(message)
    class Error(message: String) : Message(message)
}
