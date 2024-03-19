package com.androida.eventbus.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androida.eventbus.utils.Event
import com.androida.eventbus.utils.EventsViewState
import com.androida.eventbus.utils.IEventBus
import com.androida.eventbus.utils.SingleUseValue
import com.androida.eventbus.utils.toSingleUseValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class EventBusViewModel @Inject constructor(
    val eventBus: IEventBus
) : ViewModel() {

    private val _events = MutableStateFlow(EventsViewState())
    val events = _events.asStateFlow()

    init {
        eventBus.events.onEach { event ->
            when (event) {
                is Event.Navigation -> {
                    _events.update {
                        it.copy(navigationEvent = SingleUseValue(event))
                    }
                }

                is Event.ShowLoader -> {
                    _events.update {
                        it.copy(showLoader = event.show.toSingleUseValue())
                    }
                }

                is Event.ShowWarning -> {
                    _events.update {
                        it.copy(showWarning = SingleUseValue(event))
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}