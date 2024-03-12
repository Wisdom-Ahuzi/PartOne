package com.example.partone.presentation.signup

data class SignupState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
