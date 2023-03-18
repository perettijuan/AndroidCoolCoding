package com.example.movielist.di

import com.example.movielist.data.MoviesApi
import com.example.movielist.data.MoviesApiImpl
import com.example.movielist.domain.MoviesRepository
import com.example.movielist.domain.MoviesRepositoryImpl
import com.example.movielist.domain.NetworkRepository
import com.example.movielist.domain.NetworkRepositoryImpl
import com.example.movielist.network.NetworkManager
import com.jpp.core.networking.HttpClientProvider

object ModuleInstanceProvider {

    // Using service locator pattern
    private val httpClientProvider: HttpClientProvider = HttpClientProvider.locateInstance()
    private val movieApi: MoviesApi = MoviesApiImpl(httpClientProvider)
    private val moviesRepository: MoviesRepository = MoviesRepositoryImpl(movieApi)
    private var networkRepository: NetworkRepository? = null

    fun initialize(networkManager: NetworkManager) {
        networkRepository = NetworkRepositoryImpl(networkManager)
    }

    internal fun provideMoviesRepository(): MoviesRepository = moviesRepository
    internal fun provideNetworkRepository(): NetworkRepository =
        networkRepository ?: throw IllegalStateException("Needs to initialize the module first.")
}