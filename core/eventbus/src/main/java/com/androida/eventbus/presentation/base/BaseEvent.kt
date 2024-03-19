package com.androida.eventbus.presentation.base

import kotlinx.coroutines.flow.MutableStateFlow

interface BaseEvent<Data> {
    fun updateData(previousData: MutableStateFlow<Data>) = Unit
}
