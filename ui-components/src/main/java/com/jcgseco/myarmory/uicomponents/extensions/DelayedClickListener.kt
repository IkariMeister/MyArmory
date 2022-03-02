package com.jcgseco.myarmory.uicomponents.extensions

import android.os.SystemClock
import android.view.View

fun View.setDelayedOnClickListener(onClick: (View) -> Unit) {
    setOnClickListener(DelayedClickListener { onClick(it) })
}

private class DelayedClickListener(
    private var defaultInterval: Int = 1000,
    private val onDelayedCLick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked > defaultInterval) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            onDelayedCLick(v)
        }
    }
}
