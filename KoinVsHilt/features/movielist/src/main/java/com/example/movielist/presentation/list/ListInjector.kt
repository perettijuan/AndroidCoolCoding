package com.example.movielist.presentation.list

import com.example.movielist.di.AppInstanceProvider

// Fragment Scoped
object ListInjector {

    private var presenter: ListContract.Presenter? = null
    private var router: ListContract.Router? = null
    private var interactor: ListContract.Interactor? = null

    fun providePresenter(): ListContract.Presenter {
        return ListPresenterImpl(providesInteractor(), providerRouter())
    }

    fun providerRouter(): ListContract.Router {
        var safeRouter = router
        if (safeRouter == null) {
            safeRouter = ListRouterImpl()
            router = safeRouter
        }
        return safeRouter
    }

    fun providesInteractor(): ListContract.Interactor {
        var safeInteractor = interactor
        if (safeInteractor == null) {
            safeInteractor = ListInteractorImpl(AppInstanceProvider.getMoviesRepository())
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