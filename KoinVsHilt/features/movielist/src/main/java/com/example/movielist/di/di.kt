package com.example.movielist.di

import com.example.movielist.data.MoviesApi
import com.example.movielist.data.MoviesApiImpl
import com.example.movielist.domain.MoviesRepository
import com.example.movielist.domain.MoviesRepositoryImpl
import com.example.movielist.presentation.detail.movieDetailInstances
import com.example.movielist.presentation.list.movieListInstances
import com.jpp.core.networking.HttpClientProvider
import com.jpp.core.networking.NetworkModule
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

// Provides all the instances that should be singleton within the movielist module
internal val featureInstances: Module = module {
    single<MoviesApi> { MoviesApiImpl(httpClientProvider = get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(api = get()) }
}

internal object MovieListModule {
    fun init() {
        NetworkModule.init()
        loadKoinModules(
            listOf(
                featureInstances,
                movieListInstances,
                movieDetailInstances
            )
        )
    }
}



