package com.example.vipersample.viewlayer.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.vipersample.R
import com.example.vipersample.databinding.MovieListFragmentBinding
import com.example.vipersample.viewlayer.list.InteractorImpl
import com.example.vipersample.viewlayer.list.ListContract
import com.example.vipersample.viewlayer.list.PresenterImpl
import kotlinx.coroutines.Dispatchers

class MovieListFragment : Fragment(), ListContract.View  {

    private var viewBinding: MovieListFragmentBinding? = null
    private val presenter = PresenterImpl(
        InteractorImpl(), Dispatchers.IO
    )

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
        presenter.bindView(this)
    }

    override fun onDestroyView() {
        viewBinding = null
        presenter.unBindView()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewCreated()
    }

    override fun showLoading() {
        Log.d("MovieListFragment", "showLoading")
    }

    override fun hideLoading() {
        Log.d("MovieListFragment", "hideLoading")
    }

    override fun showMovies(movieList: List<ListContract.MovieItem>) {
        Log.d("MovieListFragment", "showMovies")
    }

    override fun showErrorMessage(message: String) {
        Log.d("MovieListFragment", "showErrorMessage")
    }

}