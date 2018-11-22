package com.jpp.architecturecomponents.di

import android.content.Context
import com.jpp.architecturecomponents.ApplicationImpl
import com.jpp.architecturecomponents.domain.GetItemsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(app: ApplicationImpl): Context = app

    @Provides
    @Singleton
    fun providesUseCase(): GetItemsUseCase = GetItemsUseCase()
}