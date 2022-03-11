package com.jcgseco.myarmory.core.commons.eventtracker

class GlobalProperties {

    val properties = HashMap<String, String>()

    fun isAnonymousUser(isAnonymous: Boolean) {
        properties["isAnonymousUser"] = isAnonymous.toString()
    }
}
