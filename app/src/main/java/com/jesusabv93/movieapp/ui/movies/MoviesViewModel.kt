package com.jesusabv93.movieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusabv93.data.repositories.MovieResult
import com.jesusabv93.data.repositories.MovieResult.Empty
import com.jesusabv93.data.repositories.MovieResult.Loading
import com.jesusabv93.data.repositories.MovieResult.Success
import com.jesusabv93.domain.Error
import com.jesusabv93.domain.movies.Movie
import com.jesusabv93.usecases.RequestUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val requestUpcomingMoviesUseCase: RequestUpcomingMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            requestUpcomingMoviesUseCase.invoke().collect { result ->
                when (result) {
                    is Loading -> _state.value = UiState(loading = true)
                    is Success -> _state.value = _state.value.copy(movies = result.data)
                    is Empty -> _state.value = _state.value.copy(movies = listOf())
                    is MovieResult.Error -> _state.value = UiState(error = result.error)
                }
            }
        }
    }

    fun onMovieClicked(movie: Movie) {
        _state.value = _state.value.copy(navigateTo = movie)
    }

    fun onNavigateDone() {
        _state.value = _state.value.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie>? = null,
        val navigateTo: Movie? = null,
        val error: Error? = null,
    )
}