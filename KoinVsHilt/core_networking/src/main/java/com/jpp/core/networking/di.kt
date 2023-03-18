package com.jpp.core.networking

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

internal val networkingInstances: Module = module {
    single<HttpClientProvider> { HttpClientProviderImpl() }
}

object NetworkModule {
    /**
     * CONTRACT: this method must be called in order to initialize this module.
     */
    fun init() = loadKoinModules(
        listOf(networkingInstances)
    )
}