package com.jpp.toprated.di

import com.jpp.core.networking.HttpClientProvider
import com.jpp.toprated.data.MoviesApi
import com.jpp.toprated.data.MoviesApiImpl
import com.jpp.toprated.domain.MoviesRepository
import com.jpp.toprated.domain.MoviesRepositoryImpl
import com.jpp.toprated.presentation.list.movieListInstances
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

// Provides all the instances that should be singleton within the toprated module
internal val featureInstances: Module = module {
    // TODO this should actually be in the core_networking module
    single<HttpClientProvider> { HttpClientProvider.locateInstance() }
    single<MoviesApi> { MoviesApiImpl(httpClientProvider = get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(api = get()) }
}

internal object TopRatedModule {
    fun init() = loadKoinModules(
        listOf(
            featureInstances,
            movieListInstances
        )
    )
}