package com.jesusabv93.usecases

import com.jesusabv93.data.repositories.MoviesRepository
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {

    operator fun invoke() = repository.upcomingMovies
}