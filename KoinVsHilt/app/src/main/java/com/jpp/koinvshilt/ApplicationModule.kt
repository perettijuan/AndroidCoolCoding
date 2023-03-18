package com.jpp.koinvshilt

import com.example.movielist.network.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun providesNetworkManager(): NetworkManager {
        return NetworkManagerImpl()
    }
}