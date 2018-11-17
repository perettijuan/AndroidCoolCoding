package com.jpp.navigationcomponents.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jpp.navigationcomponents.R
import com.jpp.navigationcomponents.domain.MoviesManager
import com.jpp.navigationcomponents.ui.MovieDetailFragmentArgs.fromBundle
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).unlockApp()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = fromBundle(arguments).movieId
        val movie = MoviesManager.getMovie(movieId)

        movie_detail_name.text = movie?.name
    }
}