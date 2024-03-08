package com.example.partone

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partone.domain.usecases.AppEntryUseCases
import com.example.partone.presentation.navgraph.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel(){

     var splashCondition by mutableStateOf(true)
         private set


    var startDestination by mutableStateOf(Routes.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach {shouldStartFromHomeScreen ->
            startDestination = if(shouldStartFromHomeScreen){
                Routes.SoundNavigation.route
            }else{
                Routes.AppStartNavigation.route
            }
            delay(300)
             splashCondition = false
        }.launchIn(viewModelScope)
    }
}