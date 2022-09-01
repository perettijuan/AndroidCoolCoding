package com.example.vipersample.viewlayer.list

import com.example.vipersample.datalayer.Movie
import com.example.vipersample.util.Try
import kotlinx.coroutines.*

class PresenterImpl(
    private val interactor: ListContract.Interactor,
    private val ioDispatcher: CoroutineDispatcher
) : ListContract.Presenter {

    private var viewInstance: ListContract.View? = null

    // TBD is this the best way to have a scope? Probably not.
    private var coroutineScope: CoroutineScope? = null

    override fun bindView(view: ListContract.View) {
        coroutineScope = CoroutineScope(SupervisorJob())
        viewInstance = view
    }

    override fun unBindView() {
        viewInstance = null
        coroutineScope?.cancel()
        coroutineScope = null
    }

    override fun onViewCreated() {
        coroutineScope?.launch {
            viewInstance?.showLoading()
            val response = withContext(ioDispatcher) {
                interactor.getMovies()
            }
            when (response) {
                is Try.Success -> viewInstance?.showMovies(response.value.results.map { movie -> movie.toItem() })
                is Try.Failure -> viewInstance?.showErrorMessage("Something went wrong")
            }
        }
    }

    override fun onMovieSelected(item: ListContract.MovieItem) {
        TODO("Not yet implemented")
    }


    private fun Movie.toItem(): ListContract.MovieItem {
        return ListContract.MovieItem(
            id, title
        )
    }
}