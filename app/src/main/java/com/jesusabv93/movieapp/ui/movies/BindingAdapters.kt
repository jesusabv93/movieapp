package com.jesusabv93.movieapp.ui.movies

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jesusabv93.domain.movies.Movie

@BindingAdapter("movies_items")
fun setMovieItemsAdapter(recyclerView: RecyclerView, items: List<Movie>?) {
    items?.let {
        (recyclerView.adapter as? MoviesAdapter)?.submitList(items)
    }
}