package com.jesusabv93.data.repositories

import com.jesusabv93.data.datasources.MoviesLocalDataSource
import com.jesusabv93.data.datasources.MoviesRemoteDataSource
import com.jesusabv93.domain.Error
import com.jesusabv93.domain.movies.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) {

    val upcomingMovies get() = localDataSource.movies

    fun findById(id: Int): Flow<Movie> = localDataSource.findById(id)

    suspend fun requestUpcomingMovies(): Flow<MovieResult<List<Movie>>> = flow {
        try {
            if (localDataSource.isEmpty()) {
                val page = remoteDataSource.findUpcomingMovies()
                localDataSource.save(page.results)
            }
            upcomingMovies.collect { movies ->
                if (localDataSource.isEmpty()) emit(MovieResult.Empty)
                else emit(MovieResult.Success(movies))
            }
        } catch (e: Exception) {
            emit(MovieResult.Error(Error.Unknown("")))
        }
    }
}