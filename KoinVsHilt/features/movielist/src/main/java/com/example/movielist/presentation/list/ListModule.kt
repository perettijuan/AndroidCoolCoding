package com.example.movielist.presentation.list

import com.example.movielist.di.AppInstanceProvider
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
    fun providesInteractor(): ListContract.Interactor {
        // TODO inject repository
        return ListInteractorImpl(AppInstanceProvider.getMoviesRepository())
    }

    @FragmentScoped
    @Provides
    fun providerRouter(): ListContract.Router {
        return ListRouterImpl()
    }
}