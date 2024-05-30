package com.jesusabv93.data.repositories

import com.jesusabv93.data.datasources.AuthRemoteDataSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
) {

    suspend fun authenticate(username: String, password: String) =
        authRemoteDataSource.authenticate(username, password)
}