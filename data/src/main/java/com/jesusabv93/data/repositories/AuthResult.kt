package com.jesusabv93.data.repositories

import com.jesusabv93.domain.Error as MovieError

sealed class AuthResult<out T> {
    data class Success<out T>(val data: T) : AuthResult<T>()
    data class Error(val error: MovieError) : AuthResult<Nothing>()
    object Loading : AuthResult<Nothing>()
}