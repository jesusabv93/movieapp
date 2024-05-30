package com.jesusabv93.movieapp.data.api

import com.jesusabv93.data.datasources.MoviesRemoteDataSource
import com.jesusabv93.domain.movies.Movie
import com.jesusabv93.domain.movies.MoviePage
import com.jesusabv93.movieapp.di.ApiKey
import javax.inject.Inject

class MoviesServerDataSource @Inject constructor(
    @ApiKey private val apiKey: String,
    private val remoteService: RemoteService
) : MoviesRemoteDataSource {

    override suspend fun findUpcomingMovies() =
        remoteService.getUpcomingMovies(apiKey)
            .toDomainModel()
}

private fun RemoteResult.toDomainModel() = MoviePage(
    page,
    results.map { it.toDomainModel() },
    totalPages,
    totalResults
)

private fun RemoteMovie.toDomainModel() = Movie(
    id,
    title,
    overview,
    releaseDate,
    "https://image.tmdb.org/t/p/w500$poster",
    "https://image.tmdb.org/t/p/w500$backdrop",
    voteAverage,
)