package com.example.movielist.presentation.detail

import com.jpp.core.networking.MovieDetail
import com.jpp.core.utils.Try
import kotlinx.coroutines.*

// Fragment scoped
internal class DetailPresenter(
    private val interactor: DetailContract.Interactor,
    private val router: DetailContract.Router
) : DetailContract.Presenter {

    private var viewInstance: DetailContract.View? = null

    // TODO is this the best way to have a scope? Probably not.
    private var coroutineScope: CoroutineScope? = null

    override fun bindView(view: DetailContract.View) {
        coroutineScope = CoroutineScope(SupervisorJob())
        viewInstance = view
    }

    override fun onViewCreated(id: Double) {
        coroutineScope?.launch {
            viewInstance?.showLoading()

            val response = withContext(Dispatchers.IO) {
                interactor.getMovies(id)
            }

            withContext(Dispatchers.Main) {
                when (response) {
                    is Try.Success -> {
                        viewInstance?.hideLoading()
                        viewInstance?.showDetail(response.value.toUi())
                    }
                    is Try.Failure -> viewInstance?.showErrorMessage("Something went wrong")
                }
            }
        }
    }

    override fun unBindView() {
        viewInstance = null
        coroutineScope?.cancel()
        coroutineScope = null
    }

    private fun MovieDetail.toUi(): DetailContract.MovieDetailUi {
        return DetailContract.MovieDetailUi(
            id = id,
            title = title,
            overview = overview
        )
    }
}