package com.androida.eventbus.di

import com.androida.eventbus.utils.EventBus
import com.androida.eventbus.utils.IEventBus
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @Binds
    @Singleton
    fun bindEventBus(eventBus: EventBus): IEventBus

}
