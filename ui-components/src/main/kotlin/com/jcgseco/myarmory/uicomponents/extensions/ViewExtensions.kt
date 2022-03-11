package com.jcgseco.myarmory.uicomponents.extensions

import android.os.SystemClock
import android.view.View

fun View.setDelayOnClickListener(onClick: (View) -> Unit) {
    setOnClickListener(DelayClickListener { onClick(it) })
}

private class DelayClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked > defaultInterval) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            onSafeCLick(v)
        }
    }
}
