package com.androida.handlers.ext

import android.util.Log
import androidx.lifecycle.ViewModel

fun <T> T.log(key: String = "MVI"): T {
    Log.e(key, toString())
    return this
}

val Throwable.logMessage: String get() = (message ?: "") + "\n" + stackTraceToString()

fun ViewModel.logEvent(event: Any) =
    "${this::class.java.simpleName} - Event - ${event.javaClass.simpleName}"
