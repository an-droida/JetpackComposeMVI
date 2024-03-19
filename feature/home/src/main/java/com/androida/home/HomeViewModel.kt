package com.androida.home

import androidx.lifecycle.viewModelScope
import com.androida.eventbus.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeEvent, HomeViewState>() {
    override val _viewState = MutableStateFlow(HomeViewState())

    init {
        sideEffectHandler.execute(viewModelScope) {

        }
    }
}