package com.jesusabv93.movieapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jesusabv93.domain.Error
import com.jesusabv93.domain.User
import com.jesusabv93.movieapp.databinding.ActivityAuthBinding
import com.jesusabv93.movieapp.ui.movies.MoviesActivity
import com.jesusabv93.movieapp.ui.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            viewModel.loginClicked(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString(),
            )
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect(::updateUI)
            }
        }
    }

    private fun updateUI(state: AuthViewModel.UiState) {
        state.navigateTo?.let(::navigateTo)
        state.error?.let(::showError)
    }

    private fun navigateTo(user: User) {
        toast(user.greeting())
        viewModel.onNavigateDone()
        startActivity(Intent(this, MoviesActivity::class.java))
    }

    private fun showError(error: Error) {
        toast(error.toString())
    }
}