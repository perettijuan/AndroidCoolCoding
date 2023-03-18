package com.example.movielist.presentation.detail

import com.example.movielist.di.ModuleInstanceProvider

// Fragment Scoped
internal object DetailInjector {

    // Just for simplicity
    var selectedMovieId: Double? = null

    private var presenter: DetailContract.Presenter? = null
    private var router: DetailContract.Router? = null
    private var interactor: DetailContract.Interactor? = null

    fun providePresenter(): DetailContract.Presenter {
        return DetailPresenter(providesInteractor(), providerRouter())
    }

    fun providerRouter(): DetailContract.Router {
        var safeRouter = router
        if (safeRouter == null) {
            safeRouter = DetailRouter()
            router = safeRouter
        }
        return safeRouter
    }

    fun providesInteractor(): DetailContract.Interactor {
        var safeInteractor = interactor
        if (safeInteractor == null) {
            safeInteractor = DetailInteractor(
                ModuleInstanceProvider.provideMoviesRepository(),
                ModuleInstanceProvider.provideNetworkRepository()
            )
            interactor = safeInteractor
        }
        return safeInteractor
    }

    fun onDestroy() {
        presenter = null
        router = null
        interactor = null
    }
}