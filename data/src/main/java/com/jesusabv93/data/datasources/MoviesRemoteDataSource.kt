package com.jesusabv93.data.datasources

import com.jesusabv93.domain.movies.MoviePage

interface MoviesRemoteDataSource {
    suspend fun findUpcomingMovies(): MoviePage
}