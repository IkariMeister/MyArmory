package com.jcgseco.myarmory.core.commons.actions

import android.app.Activity
import android.view.Gravity
import android.widget.Toast

interface MessageDisplayer {

    fun showMessage(activity: Activity, message: Message)
}

object Toast : MessageDisplayer {

    private var toast: Toast? = null

    override fun showMessage(activity: Activity, message: Message) {
        toast?.cancel()
        toast = Toast(activity.applicationContext).apply {
            setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0, 0)
            duration = Toast.LENGTH_LONG
            setText(message.message)
        }
        toast?.show()
    }
}
