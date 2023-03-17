package com.example.movielist.presentation.list

import org.koin.core.module.Module
import org.koin.dsl.module

// Fragment Scoped
internal val movieListInstances: Module = module {
    scope<ListFragment> {
        // Router needs to be shared between the Fragment and the Presenter
        scoped<ListContract.Router> { ListRouterImpl() }
        factory<ListContract.Interactor> { ListInteractorImpl(repository = get()) }
        factory<ListContract.Presenter> { ListPresenterImpl(interactor = get(), router = get()) }
    }
}