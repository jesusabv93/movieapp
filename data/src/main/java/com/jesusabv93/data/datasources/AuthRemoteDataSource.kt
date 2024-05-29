package com.jesusabv93.data.datasources

import com.jesusabv93.data.repositories.AuthResult
import com.jesusabv93.domain.User
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {
    suspend fun authenticate(username: String, password: String): Flow<AuthResult<User>>
}