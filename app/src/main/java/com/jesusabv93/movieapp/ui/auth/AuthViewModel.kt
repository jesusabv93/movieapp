package com.jesusabv93.movieapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusabv93.data.repositories.AuthResult
import com.jesusabv93.data.repositories.AuthResult.Loading
import com.jesusabv93.data.repositories.AuthResult.Success
import com.jesusabv93.domain.Error
import com.jesusabv93.domain.User
import com.jesusabv93.usecases.AuthenticateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun loginClicked(username: String, password: String) {
        viewModelScope.launch {
            authenticateUseCase.authenticate(username, password).collect { result ->
                when (result) {
                    is Loading -> _state.value = UiState(loading = true)
                    is Success -> _state.value = UiState(navigateTo = result.data)
                    is AuthResult.Error -> _state.value = UiState(error = result.error)
                }
            }
        }
    }

    fun onNavigateDone() {
        _state.value = _state.value.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val navigateTo: User? = null,
        val error: Error? = null,
    )
}