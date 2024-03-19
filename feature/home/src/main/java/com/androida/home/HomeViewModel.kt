package com.androida.home

import androidx.lifecycle.viewModelScope
import com.androida.eventbus.presentation.base.BaseViewModel
import com.androida.eventbus.utils.IEventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    override var eventBus: IEventBus,
) : BaseViewModel<HomeEvent, HomeViewState>() {
    override val _viewState = MutableStateFlow(HomeViewState())

    override fun onEventReceived(event: HomeEvent) {
        super.onEventReceived(event)
        when (event) {
            is HomeEvent.PostTestRequest -> {
                _viewState.update { it.copy(message = event.text) }
            }
        }
    }

    fun executeRequest() {
        sideEffectHandler.execute(viewModelScope) {

        }
    }

}