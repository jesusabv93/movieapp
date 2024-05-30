package com.jesusabv93.movieapp.data.api

import com.google.gson.annotations.SerializedName

data class RemoteResult(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<RemoteMovie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class RemoteMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("backdrop_path") val backdrop: String,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("release_date") val releaseDate: String,
)