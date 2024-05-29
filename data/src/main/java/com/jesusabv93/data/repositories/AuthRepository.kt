package com.jesusabv93.data.repositories

import com.jesusabv93.data.datasources.AuthRemoteDataSource

class AuthRepository (
    private val authRemoteDataSource: AuthRemoteDataSource,
) {

    suspend fun authenticate(username: String, password: String) =
        authRemoteDataSource.authenticate(username, password)
}