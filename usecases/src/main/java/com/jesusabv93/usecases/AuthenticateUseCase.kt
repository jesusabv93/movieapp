package com.jesusabv93.usecases

import com.jesusabv93.data.repositories.AuthRepository
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend fun authenticate(username: String, password: String) =
        repository.authenticate(username, password)
}