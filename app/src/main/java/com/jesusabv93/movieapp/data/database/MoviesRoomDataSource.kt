package com.jesusabv93.movieapp.data.database

import com.jesusabv93.data.datasources.MoviesLocalDataSource
import com.jesusabv93.domain.Error
import com.jesusabv93.domain.movies.Movie
import com.jesusabv93.movieapp.data.tryCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.jesusabv93.movieapp.data.database.Movie as DbMovie

class MoviesRoomDataSource @Inject constructor(private val movieDao: MovieDao) : MoviesLocalDataSource {

    override val movies: Flow<List<Movie>> = movieDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = movieDao.movieCount() == 0

    override fun findById(id: Int): Flow<Movie> = movieDao.findById(id).map { it.toDomainModel() }

    override suspend fun save(movies: List<Movie>): Error? = tryCall {
        movieDao.insertMovies(movies.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )
}

private fun List<DbMovie>.toDomainModel(): List<Movie> = map { it.toDomainModel() }

private fun DbMovie.toDomainModel(): Movie =
    Movie(
        id,
        title,
        overview,
        releaseDate,
        poster,
        backdrop,
        voteAverage,
    )

private fun List<Movie>.fromDomainModel(): List<DbMovie> = map { it.fromDomainModel() }

private fun Movie.fromDomainModel(): DbMovie = DbMovie(
    id,
    title,
    overview,
    releaseDate,
    poster,
    backdrop,
    voteAverage,
)