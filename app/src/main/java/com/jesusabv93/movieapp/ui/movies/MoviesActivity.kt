package com.jesusabv93.movieapp.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.jesusabv93.domain.Error
import com.jesusabv93.domain.movies.Movie
import com.jesusabv93.movieapp.databinding.ActivityMoviesBinding
import com.jesusabv93.movieapp.ui.detail.MovieDetailActivity
import com.jesusabv93.movieapp.ui.movies.MoviesViewModel.UiState
import com.jesusabv93.movieapp.ui.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews(binding)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect(::updateUI)
            }
        }
    }

    private fun setupViews(binding: ActivityMoviesBinding) {
        moviesAdapter = MoviesAdapter { movie -> viewModel.onMovieClicked(movie) }
        binding.rvItems.apply {
            layoutManager = GridLayoutManager(this@MoviesActivity, 3)
            adapter = moviesAdapter
        }
    }

    private fun updateUI(state: UiState) {
        state.navigateTo?.let(::navigateTo)
        state.error?.let(::showError)
        state.movies?.let(::setMovies)
    }

    private fun navigateTo(movie: Movie) {
        viewModel.onNavigateDone()
        startActivity(Intent(this, MovieDetailActivity::class.java).apply {
            //putExtra(MovieDetailActivity.MOVIE_ID, movie.id)
        })
    }

    private fun showError(error: Error) {
        toast(error.toString())
    }

    private fun setMovies(movies: List<Movie>) {
        moviesAdapter.submitList(movies)
    }

}