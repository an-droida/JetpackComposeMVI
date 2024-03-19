package com.androida.eventbus.utils

data class EventsViewState(
    val navigationEvent: SingleUseValue<Event.Navigation?> = SingleUseValue(null),
    val showWarning: SingleUseValue<Event.ShowWarning?> = SingleUseValue(null),
    val showLoader: SingleUseValue<Boolean> = SingleUseValue(null),
)