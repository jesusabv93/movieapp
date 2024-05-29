package com.jesusabv93.domain

data class User(
    val username: String,
) {
    fun greeting(): String {
        return "Bienvenido $username"
    }
}