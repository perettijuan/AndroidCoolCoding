package com.example.movielist.di

import com.example.movielist.data.MoviesApi
import com.example.movielist.data.MoviesApiImpl
import com.example.movielist.domain.MoviesRepository
import com.example.movielist.domain.MoviesRepositoryImpl
import com.jpp.core.networking.HttpClientProvider

internal object AppInstanceProvider {

    // Using service locator pattern
    private val httpClientProvider: HttpClientProvider = HttpClientProvider.locateInstance()
    private val movieApi: MoviesApi = MoviesApiImpl(httpClientProvider)
    private val moviesRepository: MoviesRepository = MoviesRepositoryImpl(movieApi)

    fun provideMoviesRepository(): MoviesRepository = moviesRepository
}