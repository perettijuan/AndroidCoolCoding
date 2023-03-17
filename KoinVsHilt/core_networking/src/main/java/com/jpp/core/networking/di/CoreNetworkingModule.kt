package com.jpp.core.networking.di

import com.jpp.core.networking.HttpClientProvider
import com.jpp.core.networking.HttpClientProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreNetworkingModule {

    @Singleton
    @Provides
    fun providesHttpClientProvider(): HttpClientProvider {
        return HttpClientProviderImpl()
    }

}