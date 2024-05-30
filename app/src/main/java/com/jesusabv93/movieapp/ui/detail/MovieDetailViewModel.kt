package com.jesusabv93.movieapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusabv93.domain.Error
import com.jesusabv93.domain.movies.Movie
import com.jesusabv93.usecases.FindMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val findMovieUseCase: FindMovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun finMovie(movieId: Int?) {
        if (movieId == null) _state.value =
            UiState(error = Error.Unknown("Movie not found"))
        else
            viewModelScope.launch {
                findMovieUseCase.invoke(movieId).collect { movie ->
                    _state.value = UiState(movie = movie)
                }
            }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null,
        val error: Error? = null,
    )
}