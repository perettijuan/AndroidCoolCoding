package com.jpp.toprated.di

import com.jpp.core.networking.HttpClientProvider
import com.jpp.toprated.data.MoviesApi
import com.jpp.toprated.data.MoviesApiImpl
import com.jpp.toprated.domain.MoviesRepository
import com.jpp.toprated.domain.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TopRatedModule {
    @Singleton
    @Provides
    fun provideMoviesRepository(httpClientProvider: HttpClientProvider): MoviesRepository {
        val movieApi: MoviesApi = MoviesApiImpl(httpClientProvider)
        return MoviesRepositoryImpl(movieApi)
    }
}