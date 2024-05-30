package com.jesusabv93.usecases

import com.jesusabv93.data.repositories.MoviesRepository
import javax.inject.Inject

class RequestUpcomingMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke() = moviesRepository.requestUpcomingMovies()
}