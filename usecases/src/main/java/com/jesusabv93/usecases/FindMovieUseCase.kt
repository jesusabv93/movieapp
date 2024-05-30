package com.jesusabv93.usecases

import com.jesusabv93.data.repositories.MoviesRepository
import com.jesusabv93.domain.movies.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindMovieUseCase @Inject constructor(private val repository: MoviesRepository) {

    operator fun invoke(id: Int): Flow<Movie> = repository.findById(id)
}