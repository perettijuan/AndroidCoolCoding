package com.jpp.navigationcomponents.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jpp.navigationcomponents.R
import com.jpp.navigationcomponents.domain.Movie
import com.jpp.navigationcomponents.domain.MoviesManager
import kotlinx.android.synthetic.main.movies_list_fragment.*

class MoviesListFragment : Fragment() {

    private val moviesAdapter by lazy {
        MoviesAdapter { movie, textView ->
            val extras = FragmentNavigatorExtras(textView to "secondTransitionName")
            val bundle = Bundle()
            bundle.putInt("movie_id", movie.id)
            findNavController().navigate(R.id.movieDetailFragment, bundle, null, extras)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        moviesAdapter.updateMovies(MoviesManager.getMovieList())

        fab.setOnClickListener {
            MoviesManager.addMovie("Name ${moviesAdapter.itemCount}")
            moviesAdapter.updateMovies(MoviesManager.getMovieList())
        }
    }

    private fun setupRecyclerView() {
        moviesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        moviesRecyclerView.adapter = moviesAdapter
        moviesRecyclerView.setHasFixedSize(true)
    }


    class MoviesAdapter(private var movieList: List<Movie> = emptyList(), private val clickListener: (Movie, TextView) -> Unit) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false))

        override fun getItemCount() = movieList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindMovie(movieList[position], clickListener)
        }

        fun updateMovies(items: List<Movie>) {
            val diffResult = DiffUtil.calculateDiff(MovieDiffCallback(this.movieList, items))
            this.movieList = items
            diffResult.dispatchUpdatesTo(this)
        }


        class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

            fun bindMovie(movie: Movie, clickListener: (Movie, TextView) -> Unit) {
                itemView.findViewById<TextView>(R.id.movie_name).text = movie.name
                itemView.setOnClickListener {
                    clickListener(movie, itemView.findViewById(R.id.movie_name))
                }
            }

        }
    }

    class MovieDiffCallback(private val old: List<Movie>,
                            private val new: List<Movie>) : DiffUtil.Callback() {
        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldIndex: Int, newIndex: Int): Boolean {
            return old[oldIndex].name == new[newIndex].name
        }

        override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean {
            return old[oldIndex] == new[newIndex]
        }
    }

}