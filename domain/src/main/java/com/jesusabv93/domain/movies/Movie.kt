package com.jesusabv93.domain.movies

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String,
    val backdrop: String,
    val voteAverage: Double,
)