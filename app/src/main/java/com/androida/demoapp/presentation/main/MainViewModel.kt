package com.androida.demoapp.presentation.main

import com.androida.eventbus.presentation.base.BaseViewModel
import com.androida.handlers.SideEffectHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainEvent, MainViewState>() {

    override val _viewState = MutableStateFlow(MainViewState())

}