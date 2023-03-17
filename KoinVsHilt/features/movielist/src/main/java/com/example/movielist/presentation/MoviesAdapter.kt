package com.example.movielist.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.ListItemBinding

class MoviesAdapter(private val movieSelectionListener: (ListContract.MovieItem) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val items = mutableListOf<ListContract.MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovie(items[position], movieSelectionListener)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged") // for simplicity, this is a sample app!
    fun setItems(newItems: List<ListContract.MovieItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(private val itemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindMovie(
            item: ListContract.MovieItem,
            movieSelectionListener: (ListContract.MovieItem) -> Unit
        ) {
            itemBinding.movieName.text = item.name
            itemView.setOnClickListener { movieSelectionListener(item) }
        }
    }
}