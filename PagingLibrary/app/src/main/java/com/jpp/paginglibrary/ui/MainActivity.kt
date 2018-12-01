package com.jpp.paginglibrary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jpp.paginglibrary.R
import com.jpp.paginglibrary.datalayer.Movie
import kotlinx.android.synthetic.main.main_activity_layout.*
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        moviesList.layoutManager = LinearLayoutManager(this)


        val adapter = MoviesAdapter()

        viewModel.getMovieList().observe(this, Observer<PagedList<Movie>> {
            adapter.submitList(it)
        })

        viewModel.getViewState().observe(this, Observer {
            statusTextView.text = when (it) {
                MoviesViewState.Loading -> "loading"
                MoviesViewState.Error -> "Error"
                MoviesViewState.Loaded -> "Loaded"
            }
        })

        moviesList.adapter = adapter
    }

    class MoviesAdapter : PagedListAdapter<Movie, MoviesAdapter.ViewHolder>(MovieDiffCallback()) {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            getItem(position)?.let {
                holder.bindMovie(it)
            }
        }


        class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

            fun bindMovie(movie: Movie) {
                itemView.movieTitle.text = movie.title
                itemView.movieOverview.text = movie.overview
            }

        }
    }


    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }
}