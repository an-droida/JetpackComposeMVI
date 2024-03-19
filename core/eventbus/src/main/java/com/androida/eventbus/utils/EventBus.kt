package com.androida.eventbus.utils

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventBus @Inject constructor() : IEventBus {

    private val _events = MutableSharedFlow<Event>()

    override val events: Flow<Event> = _events

    @OptIn(DelicateCoroutinesApi::class)
    override fun produceEvent(event: Event) {
        GlobalScope.launch(Dispatchers.Main.immediate) {
            _events.emit(event)
        }
    }
}