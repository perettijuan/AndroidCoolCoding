package com.jpp.architecturecomponents.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jpp.architecturecomponents.R
import kotlinx.android.synthetic.main.main_activity_layout.*

/**
 * This is a very simple application that will, in time, show a list of items. The main goal
 * of this application is to get my head around Android Architecture components and how can
 * we achieve a good architecture of a typical Android application.
 *
 * Initially, I'm going to base this architecture referring to an MVI (Model-View-Intent) implementation.
 *
 * I get the inspiration from this blog post: http://hannesdorfmann.com/android/mosby3-mvi-1
 * even though there are a few things that I don't like it about it.
 *
 * What I don't want to do?
 *
 * 1 - I don't want to use Rx. Why? Because I think it is a huge library to do the thins we usually
 * do in an Android application. I mean, is like using a canon to kill a mosquito, in the sense
 * that we're adding a huge library to do functional programing and manage multi-threading - and we
 * can do that with Kotlin and a few other things. At least, that's my premise and I'll try to
 * prove that with this example app.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        itemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListAdapter()
        }

        /*
         * From Google samples:
         * Re-created activities receive the same MyViewModel instance created by the first activity.
         */
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java).apply {
            bindViewState().observe(this@MainActivity, Observer { activityState ->
                renderState(activityState)
            })
        }

        btnLoadMoreItems.setOnClickListener {
            viewModel.onIntent(MainActivityIntent.LoadMoreItems)
        }

        if (savedInstanceState == null) {
            viewModel.onIntent(MainActivityIntent.LoadMoreItems)
        }
    }


    private fun renderState(state: MainActivityViewState) {
        when (state) {
            MainActivityViewState.Loading -> {
                progressBar.visibility = VISIBLE
                txtError.visibility = INVISIBLE
                itemsRecyclerView.visibility = INVISIBLE
                btnLoadMoreItems.visibility = INVISIBLE
            }

            MainActivityViewState.Error -> {
                progressBar.visibility = INVISIBLE
                txtError.visibility = VISIBLE
                itemsRecyclerView.visibility = INVISIBLE
                btnLoadMoreItems.visibility = INVISIBLE
            }

            is MainActivityViewState.Items -> {
                (itemsRecyclerView.adapter as ListAdapter).addItems(state.items)
                progressBar.visibility = INVISIBLE
                txtError.visibility = INVISIBLE
                itemsRecyclerView.visibility = VISIBLE
                btnLoadMoreItems.visibility = VISIBLE
            }
        }
    }

    class ListAdapter(private var listItems: List<UiItem> = emptyList()) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int = listItems.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItem(listItems[position])
        }

        fun addItems(items: List<UiItem>) {
            items.let {
                val diffUtil = DiffUtil.calculateDiff(UiItemDiffCallback(listItems, it))
                listItems = it
                diffUtil.dispatchUpdatesTo(this)
            }
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bindItem(uiItem: UiItem) {
                itemView.findViewById<TextView>(android.R.id.text1).text = uiItem.text
            }
        }

        class UiItemDiffCallback(private val old: List<UiItem>,
                                 private val new: List<UiItem>) : DiffUtil.Callback() {

            override fun areItemsTheSame(oldIndex: Int, newIndex: Int): Boolean {
                return old[oldIndex].text == new[newIndex].text
            }

            override fun getOldListSize() = old.size

            override fun getNewListSize() = new.size

            override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean {
                return old[oldIndex] == new[newIndex]
            }

        }
    }
}