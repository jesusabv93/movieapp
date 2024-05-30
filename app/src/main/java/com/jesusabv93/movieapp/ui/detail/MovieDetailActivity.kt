package com.jesusabv93.movieapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jesusabv93.domain.Error
import com.jesusabv93.domain.movies.Movie
import com.jesusabv93.movieapp.databinding.ActivityMovieDetailBinding
import com.jesusabv93.movieapp.ui.detail.MovieDetailViewModel.UiState
import com.jesusabv93.movieapp.ui.utils.loadUrl
import com.jesusabv93.movieapp.ui.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "movieId"
    }

    private val viewModel: MovieDetailViewModel by viewModels()
    private val movieId by lazy { intent.extras?.getInt(MOVIE_ID) }

    lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.finMovie(movieId)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect(::updateUI)
            }
        }
    }

    private fun updateUI(state: UiState) {
        state.movie?.let(::showMovie)
        state.error?.let(::showError)
    }

    private fun showMovie(movie: Movie) {
        binding.toolbar.title = movie.title
        binding.ivPoster.loadUrl(movie.backdrop)
        binding.tvMovieDetails.text = buildSpannedString {
            bold { append("Title: ") }
            appendLine(movie.title)

            bold { append("Release date: ") }
            appendLine(movie.releaseDate)

            bold { append("Vote Average: ") }
            appendLine(movie.voteAverage.toString())

            bold { append("Overview: ") }
            appendLine()
            appendLine(movie.overview)
        }

    }

    private fun showError(error: Error) {
        toast(error.toString())
    }
}