package com.example.movielist.presentation.detail

import org.koin.core.module.Module
import org.koin.dsl.module

// Fragment Scoped
internal val movieDetailInstances: Module = module {
    scope<DetailFragment> {
        // Router needs to be shared between the Fragment and the Presenter
        scoped<DetailContract.Router> { DetailRouter() }
        factory<DetailContract.Interactor> { DetailInteractor(repository = get(), networkRepository = get()) }
        factory<DetailContract.Presenter> { DetailPresenter(interactor = get(), router = get()) }
    }
}

internal object DetailInjector {

    // Just for simplicity
    var selectedMovieId: Double? = null
}