package com.example.movielist.di

import com.example.movielist.data.MoviesApi
import com.example.movielist.data.MoviesApiImpl
import com.example.movielist.domain.MoviesRepository
import com.example.movielist.domain.MoviesRepositoryImpl
import com.jpp.core.networking.HttpClientProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class FeatureModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(httpClientProvider: HttpClientProvider): MoviesRepository {
        val movieApi: MoviesApi = MoviesApiImpl(httpClientProvider)
        return MoviesRepositoryImpl(movieApi)
    }
}