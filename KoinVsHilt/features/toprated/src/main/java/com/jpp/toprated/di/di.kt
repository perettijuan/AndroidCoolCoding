package com.jpp.toprated.di

import com.jpp.core.networking.NetworkModule
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
    single<MoviesApi> { MoviesApiImpl(httpClientProvider = get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(api = get()) }
}

internal object TopRatedModule {
    fun init() {
        /**
         * PROBLEM WITH THIS APPROACH: it is not entirely clear that the
         * module has a dependency that comes from the NetworkModule and, for that
         * reason, NetworkModule needs to be initialized.
         *
         * 2nd PROBLEM WITH THIS APPROACH: NetworkModule.init() is being initialized
         * multiple times. Easy to solve but still, needs to be handled to ensure idempotency.
         */
        NetworkModule.init()
        loadKoinModules(
            listOf(
                featureInstances,
                movieListInstances
            )
        )
    }
}