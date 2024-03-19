package com.androida.eventbus.presentation.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androida.eventbus.utils.Event
import com.androida.eventbus.utils.IEventBus
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel<ViewAction : BaseEvent<ViewState>, ViewState> : ViewModel() {

    protected abstract val _viewState: MutableStateFlow<ViewState>
    val viewState by lazy { _viewState.asStateFlow() }

    private val actionSubject: MutableSharedFlow<ViewAction> = MutableSharedFlow()


    @Inject
    open lateinit var eventBus: IEventBus

    init {
        actionSubject
            .onEach(::processAction)
            .launchIn(viewModelScope)
    }

    @CallSuper
    protected open fun onActionReceived(viewAction: ViewAction) {
        if (viewAction is Event) {
            eventBus.produceEvent(viewAction)
        }
    }

    private fun processAction(viewAction: ViewAction) {
        onActionReceived(viewAction)
    }

    fun postAction(action: ViewAction) {
        viewModelScope.launch {
            action.updateData(_viewState)
            actionSubject.emit(action)
        }
    }
}