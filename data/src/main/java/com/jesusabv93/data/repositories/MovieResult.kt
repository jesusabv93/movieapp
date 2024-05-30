package com.jesusabv93.data.repositories

import com.jesusabv93.domain.Error as MovieError

sealed class MovieResult<out T> {
    data class Success<out T>(val data: T) : MovieResult<T>()
    object Empty : MovieResult<Nothing>()
    data class Error(val error: MovieError) : MovieResult<Nothing>()
    object Loading : MovieResult<Nothing>()
}