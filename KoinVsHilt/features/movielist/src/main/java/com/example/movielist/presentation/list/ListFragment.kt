package com.example.movielist.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.databinding.MovieListFragmentBinding
import com.example.movielist.di.MovieListModule
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope

internal class ListFragment : Fragment(), ListContract.View, AndroidScopeComponent {

    private var viewBinding: MovieListFragmentBinding? = null
    private val router: ListContract.Router by inject()
    private val presenter: ListContract.Presenter by inject()

    // This is not entirely clear. In order to use scope,
    // we need to implement AndroidScopeComponent
    override val scope: Scope by fragmentScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is actually very interesting. All dependencies
        // can be internal with this approach.
        MovieListModule.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = MovieListFragmentBinding.inflate(inflater)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router.bind(findNavController())
        presenter.bindView(this)
    }

    override fun onDestroyView() {
        viewBinding = null
        router.unBind()
        presenter.unBindView()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewCreated()
    }

    override fun showLoading() {
        viewBinding?.movieList?.visibility = View.INVISIBLE
        viewBinding?.errorMessage?.visibility = View.INVISIBLE
        viewBinding?.loadingMovies?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        viewBinding?.loadingMovies?.visibility = View.INVISIBLE
    }

    override fun showMovies(movieList: List<ListContract.MovieItem>) {
        viewBinding?.movieList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MoviesAdapter { item -> presenter.onMovieSelected(item) }.apply {
                setItems(movieList)
            }
        }
        viewBinding?.errorMessage?.visibility = View.INVISIBLE
        viewBinding?.loadingMovies?.visibility = View.INVISIBLE
        viewBinding?.movieList?.visibility = View.VISIBLE
    }

    override fun showErrorMessage(message: String) {
        viewBinding?.movieList?.visibility = View.INVISIBLE
        viewBinding?.loadingMovies?.visibility = View.INVISIBLE
        viewBinding?.errorMessage?.visibility = View.VISIBLE
        viewBinding?.errorMessage?.text = message
    }
}