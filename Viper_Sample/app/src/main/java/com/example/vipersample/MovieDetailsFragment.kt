package com.example.vipersample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vipersample.databinding.MovieDetailsFragmentBinding

class MovieDetailsFragment : Fragment() {

    private var viewBinding: MovieDetailsFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = MovieDetailsFragmentBinding.inflate(inflater)
        return viewBinding?.root
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }
}