package com.jesusabv93.movieapp.data.mock

import com.jesusabv93.data.datasources.AuthRemoteDataSource
import com.jesusabv93.data.repositories.AuthResult
import com.jesusabv93.domain.Error
import com.jesusabv93.domain.User
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class AuthMockDataSource : AuthRemoteDataSource {

    override suspend fun authenticate(username: String, password: String) = flow {
        if ((username == "Admin").and(password == "Password*123"))
            emit(AuthResult.Success(User(username)))
        else
            emit(AuthResult.Error(Error.Unknown("Credentials are invalid.")))
    }.onStart { emit(AuthResult.Loading) }
}