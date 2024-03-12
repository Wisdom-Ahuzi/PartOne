package com.example.partone.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partone.utilities.Resource
import com.example.partone.firebaseAuth.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository:AuthRepository
) :ViewModel() {

    val _loginState = Channel<LoginState>()
    val loginState =  _loginState.receiveAsFlow()


    fun loginUser(email:String, password:String) = viewModelScope.launch{
        repository.loginUser(email,password).collect{result ->
            when(result) {
                is Resource.Success -> {
                    _loginState.send(LoginState(isSuccess = "Sign in successfully!"))
                }
                is Resource.Loading -> {
                    _loginState.send(LoginState(isLoading = true))
                }
                is Resource.Error -> {
                    _loginState.send(LoginState(isError = result.message))
                }
            }
        }
    }
}