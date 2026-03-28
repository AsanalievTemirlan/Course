package com.example.presentation.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel

class AuthViewModel(): ViewModel() {

    private val emailRegex =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    fun login(email: String, password: String): Boolean {
        val isValidEmail = emailRegex.matches(email)
        val isValidPassword = password.isNotBlank()
        Log.d("AuthViewModel", "Email is valid: $isValidEmail")

        return isValidEmail && isValidPassword
    }

}