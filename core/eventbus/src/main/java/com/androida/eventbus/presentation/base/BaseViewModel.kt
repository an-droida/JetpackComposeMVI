package com.androida.eventbus.presentation.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androida.eventbus.utils.Event
import com.androida.eventbus.utils.IEventBus
import com.androida.handlers.SideEffectHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel<ViewEvent : BaseEvent<ViewState>, ViewState> : ViewModel() {

    protected abstract val _viewState: MutableStateFlow<ViewState>
    val viewState by lazy { _viewState.asStateFlow() }

    private val eventSubject: MutableSharedFlow<ViewEvent> = MutableSharedFlow()

    @Inject
    lateinit var sideEffectHandler: SideEffectHandler

    @Inject
    open lateinit var eventBus: IEventBus

    init {
        eventSubject
            .onEach(::processEvent)
            .launchIn(viewModelScope)
    }

    @CallSuper
    protected open fun onEventReceived(viewEvent: ViewEvent) {
        if (viewEvent is Event) {
            eventBus.produceEvent(viewEvent)
        }
    }

    private fun processEvent(viewEvent: ViewEvent) {
        onEventReceived(viewEvent)
    }

    fun postEvent(event: ViewEvent) {
        viewModelScope.launch {
            event.updateData(_viewState)
            eventSubject.emit(event)
        }
    }
}