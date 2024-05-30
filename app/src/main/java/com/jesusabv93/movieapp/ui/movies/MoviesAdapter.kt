package com.jesusabv93.movieapp.ui.movies

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jesusabv93.domain.movies.Movie
import com.jesusabv93.movieapp.R
import com.jesusabv93.movieapp.databinding.MovieItemBinding
import com.jesusabv93.movieapp.ui.utils.basicDiffUtil
import com.jesusabv93.movieapp.ui.utils.inflate
import com.jesusabv93.movieapp.ui.utils.loadUrl

class MoviesAdapter(private val listener: (Movie) -> Unit) :
    ListAdapter<Movie, MoviesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.movie_item, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MovieItemBinding.bind(view)
        fun bind(movie: Movie) {
            binding.ivPoster.loadUrl(movie.poster)
        }
    }
}