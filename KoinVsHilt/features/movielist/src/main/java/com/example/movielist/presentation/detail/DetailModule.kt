package com.example.movielist.presentation.detail

import com.example.movielist.domain.MoviesRepository
import com.example.movielist.domain.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
internal class DetailModule {

    @FragmentScoped
    @Provides
    fun providePresenter(
        interactor: DetailContract.Interactor,
        router: DetailContract.Router
    ): DetailContract.Presenter {
        return DetailPresenter(interactor, router)
    }

    @FragmentScoped
    @Provides
    fun providerRouter(): DetailContract.Router {
        return DetailRouter()
    }

    @FragmentScoped
    @Provides
    fun providesInteractor(
        repository: MoviesRepository,
        networkRepository: NetworkRepository
    ): DetailContract.Interactor {
        return DetailInteractor(repository, networkRepository)
    }
}