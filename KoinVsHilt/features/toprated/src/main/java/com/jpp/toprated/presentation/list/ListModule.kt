package com.jpp.toprated.presentation.list

import com.jpp.toprated.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class ListModule {

    @FragmentScoped
    @Provides
    fun providePresenter(
        interactor: ListContract.Interactor,
        router: ListContract.Router
    ): ListContract.Presenter {
        return ListPresenterImpl(interactor, router)
    }

    @FragmentScoped
    @Provides
    fun providesInteractor(moviesRepository: MoviesRepository): ListContract.Interactor {
        return ListInteractorImpl(moviesRepository)
    }

    @FragmentScoped
    @Provides
    fun providerRouter(): ListContract.Router {
        return ListRouterImpl()
    }
}