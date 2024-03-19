package com.androida.eventbus.utils

import kotlinx.coroutines.flow.Flow


interface IEventBus {
    val events: Flow<Event>
    fun produceEvent(event: Event)
}
