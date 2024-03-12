package com.example.partone.presentation.home

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class HomeViewModel(): ViewModel() {

    @Composable
    fun PickDocument(){
        val result = remember {
            mutableStateOf<Uri?>(null)
        }
        
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()){
            result.value = it
        }

    }
}