package com.androida.eventbus.utils


sealed interface Event {

    interface Navigation : Event {
        val direction: String
        val addToBackstack: Boolean
    }

    open class ShowLoader(val show: Boolean) : Event

    open class ShowWarning(val message: String?, val secondaryMessage: String? = "") : Event
}