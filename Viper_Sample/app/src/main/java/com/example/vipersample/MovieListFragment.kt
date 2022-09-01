package com.example.vipersample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.vipersample.databinding.MovieListFragmentBinding

class MovieListFragment : Fragment() {

    private var viewBinding: MovieListFragmentBinding? = null

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
        viewBinding?.toNext?.setOnClickListener {
            findNavController().navigate(R.id.movieDetailsFragment)
        }
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }
}