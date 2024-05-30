package com.jesusabv93.domain.movies

data class MoviePage(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)