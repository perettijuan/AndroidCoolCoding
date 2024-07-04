package com.jpp.usercentricschallenge.di

import com.jpp.usercentricschallenge.extras.OnScreenActivityProvider
import com.jpp.usercentricschallenge.extras.OnScreenActivityProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * Application's hilt module: contains all singleton instances that are essential for application
 * wide usage.
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationHiltModule {

    @Singleton
    @Provides
    @ApplicationCoroutineScopeQualifier
    fun provideApplicationScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    @Singleton
    @Provides
    fun providesOnScreenActivityProvider(): OnScreenActivityProvider = OnScreenActivityProviderImpl()
}