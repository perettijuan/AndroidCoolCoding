package com.example.movielist.di

import com.example.movielist.data.MoviesApi
import com.example.movielist.data.MoviesApiImpl
import com.example.movielist.domain.MoviesRepository
import com.example.movielist.domain.MoviesRepositoryImpl
import com.example.movielist.presentation.detail.movieDetailInstances
import com.example.movielist.presentation.list.movieListInstances
import com.jpp.core.networking.HttpClientProvider
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

// Provides all the instances that should be singleton within the movielist module
internal val featureInstances: Module = module {
    // TODO this should actually be in the core_networking module
    single<HttpClientProvider> { HttpClientProvider.locateInstance() }
    single<MoviesApi> { MoviesApiImpl(httpClientProvider = get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(api = get()) }
}

object MovieListModule {
    fun init() = loadKoinModules(
        listOf(
            featureInstances,
            movieListInstances,
            movieDetailInstances
        )
    )
}



