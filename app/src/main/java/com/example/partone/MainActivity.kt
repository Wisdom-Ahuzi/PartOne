package com.example.partone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraph
import com.example.partone.presentation.AuthenticationNavigator.AuthenticationNavigator
import com.example.partone.ui.theme.PartOneTheme
import com.example.partone.presentation.login.LoginScreen
import com.example.partone.presentation.navgraph.NavGraph
import com.example.partone.presentation.signup.SignupScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }

        setContent {
            PartOneTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination )
                }

            }
        }
    }
}
