package com.example.movielist.presentation.list

import com.jpp.core.networking.Movie
import com.jpp.core.utils.Try
import kotlinx.coroutines.*

// Fragment scoped
internal class ListPresenterImpl(
    private val interactor: ListContract.Interactor,
    private val router: ListContract.Router
) : ListContract.Presenter {

    private var viewInstance: ListContract.View? = null

    // TODO is this the best way to have a scope? Probably not.
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

            val response = withContext(Dispatchers.IO) {
                interactor.getMovies()
            }

            withContext(Dispatchers.Main) {
                when (response) {
                    is Try.Success -> viewInstance?.showMovies(response.value.results.map { movie -> movie.toItem() })
                    is Try.Failure -> viewInstance?.showErrorMessage("Something went wrong")
                }
            }
        }
    }

    override fun onMovieSelected(item: ListContract.MovieItem) {
        router.openMovieDetail(item)
    }


    private fun Movie.toItem(): ListContract.MovieItem {
        return ListContract.MovieItem(
            id, title
        )
    }
}