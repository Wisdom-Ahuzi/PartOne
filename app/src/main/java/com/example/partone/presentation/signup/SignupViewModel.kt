package com.example.partone.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partone.firebaseAuth.data.AuthRepository
import com.example.partone.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val _signUpState = Channel<SignupState>()
    val signUpState =  _signUpState.receiveAsFlow()


    fun registerUser(email:String, password:String) = viewModelScope.launch{
        repository.registerUser(email,password).collect{result ->
            when(result) {
                is Resource.Success -> {
                    _signUpState.send(SignupState(isSuccess = "Sign in successfully!"))
                }
                is Resource.Loading -> {
                    _signUpState.send(SignupState(isLoading = true))
                }
                is Resource.Error -> {
                    _signUpState.send(SignupState(isError = result.message))
                }
            }
        }
    }
}