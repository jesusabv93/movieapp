package com.jesusabv93.movieapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
    ): RemoteResult

}