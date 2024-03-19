package com.androida.home

import com.androida.eventbus.presentation.base.BaseEvent

sealed interface HomeEvent : BaseEvent<HomeViewState> {

    data class PostTestRequest(val text: String) : HomeEvent


}