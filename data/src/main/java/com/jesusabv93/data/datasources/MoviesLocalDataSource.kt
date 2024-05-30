package com.jesusabv93.data.datasources

import com.jesusabv93.domain.Error
import com.jesusabv93.domain.movies.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    val movies: Flow<List<Movie>>

    suspend fun isEmpty(): Boolean

    fun findById(id: Int): Flow<Movie>

    suspend fun save(movies: List<Movie>): Error?
}