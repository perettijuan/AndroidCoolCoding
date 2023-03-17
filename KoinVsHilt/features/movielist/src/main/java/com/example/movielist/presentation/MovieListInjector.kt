package com.example.movielist.presentation

import com.example.movielist.di.AppInstanceProvider

// Fragment Scoped
object MovieListInjector {

    private var presenter: ListContract.Presenter? = null
    private var router: ListContract.Router? = null
    private var interactor: ListContract.Interactor? = null

    fun providePresenter(): ListContract.Presenter {
        return PresenterImpl(providesInteractor(), providerRouter())
    }

    fun providerRouter(): ListContract.Router {
        var safeRouter = router
        if (safeRouter == null) {
            safeRouter = RouterImpl()
            router = safeRouter
        }
        return safeRouter
    }

    fun providesInteractor(): ListContract.Interactor {
        var safeInteractor = interactor
        if (safeInteractor == null) {
            safeInteractor = InteractorImpl(AppInstanceProvider.getMoviesRepository())
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