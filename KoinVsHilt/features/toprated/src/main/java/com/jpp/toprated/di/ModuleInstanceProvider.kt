package com.jpp.toprated.di

import com.jpp.core.networking.HttpClientProvider
import com.jpp.toprated.data.MoviesApi
import com.jpp.toprated.data.MoviesApiImpl
import com.jpp.toprated.domain.MoviesRepository
import com.jpp.toprated.domain.MoviesRepositoryImpl

object ModuleInstanceProvider {
    

    // Using service locator pattern
    private val httpClientProvider: HttpClientProvider = HttpClientProvider.locateInstance()
    private val movieApi: MoviesApi = MoviesApiImpl(httpClientProvider)
    private val moviesRepository: MoviesRepository = MoviesRepositoryImpl(movieApi)

    fun getMoviesRepository(): MoviesRepository = moviesRepository
}