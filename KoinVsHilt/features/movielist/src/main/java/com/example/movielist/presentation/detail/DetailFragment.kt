package com.example.movielist.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movielist.databinding.MovieDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class DetailFragment : Fragment(), DetailContract.View {

    private var viewBinding: MovieDetailFragmentBinding? = null

    @Inject
    lateinit var router: DetailContract.Router

    @Inject
    lateinit var presenter: DetailContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = MovieDetailFragmentBinding.inflate(inflater)
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
        val id = DetailParam.selectedMovieId ?: return
        presenter.onViewCreated(id)
    }

    override fun showLoading() {
        viewBinding?.overview?.visibility = View.INVISIBLE
        viewBinding?.title?.visibility = View.INVISIBLE
        viewBinding?.loading?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        viewBinding?.loading?.visibility = View.INVISIBLE
    }

    override fun showDetail(detail: DetailContract.MovieDetailUi) {
        viewBinding?.overview?.text = detail.overview
        viewBinding?.overview?.visibility = View.VISIBLE
        viewBinding?.title?.text = detail.title
        viewBinding?.title?.visibility = View.VISIBLE
        viewBinding?.loading?.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), "Upps", Toast.LENGTH_LONG).show()
    }
}