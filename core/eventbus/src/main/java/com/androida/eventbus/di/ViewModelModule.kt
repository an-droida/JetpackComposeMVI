package com.androida.eventbus.di

import com.androida.eventbus.utils.IEventBus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    lateinit var eventBus: IEventBus

    @Provides
    @ViewModelScoped
    fun provideCommandHandler(): IEventBus {
        return eventBus
    }

}