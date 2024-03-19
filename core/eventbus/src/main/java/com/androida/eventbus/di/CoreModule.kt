package com.ips.android.core.di

import com.ips.android.core.data.preferences.manager.PreferencesManager
import com.ips.android.core.data.preferences.manager.PreferencesManagerImpl
import com.ips.android.core.data.repository.ChangeUserCredentialsRepositoryImpl
import com.ips.android.core.data.repository.UserCredentialsRepositoryImpl
import com.ips.android.core.data.repository.UserProfileRepositoryImpl
import com.ips.android.core.domain.repository.ChangeUserCredentialsRepository
import com.ips.android.core.domain.repository.UserCredentialsRepository
import com.ips.android.core.domain.repository.UserProfileRepository
import com.ips.android.core.util.biometrics.CryptoManager
import com.ips.android.core.util.biometrics.CryptographyManagerImpl
import com.ips.android.core.util.events.EventBus
import com.ips.android.core.util.events.IEventBus
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @Binds
    @Singleton
    fun bindEventBus(eventBus: EventBus): IEventBus

    @Binds
    @Singleton
    fun bingUserCredentialsRepository(userCredentialsRepositoryImpl: UserCredentialsRepositoryImpl): UserCredentialsRepository

    @Binds
    @Singleton
    fun bindUserProfileRepository(userProfileRepositoryImpl: UserProfileRepositoryImpl): UserProfileRepository

    @Binds
    @Singleton
    fun bindUserCredsChangeRepository(
        changeUserCredentialsRepositoryImpl: ChangeUserCredentialsRepositoryImpl
    ): ChangeUserCredentialsRepository

    @Binds
    @Singleton
    fun bindPreferencesManager(preferencesManagerImpl: PreferencesManagerImpl): PreferencesManager

    @Binds
    @Singleton
    fun bindCryptoManager(cryptographyManagerImpl: CryptographyManagerImpl): CryptoManager

    companion object {
        @Provides
        @Named("io")
        fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @Named("main")
        fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @Provides
        @Named("default")
        fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
    }
}
