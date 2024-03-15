package com.example.partone.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.partone.presentation.AuthenticationNavigator.AuthenticationNavigator
import com.example.partone.presentation.onboarding.OnBoardingScreen
import com.example.partone.presentation.onboarding.OnBoardingViewModel
import com.example.partone.presentation.soundNavigator.SoundNavigator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun NavGraph(
    startDestination:String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination ){
        navigation(
            route = Routes.AppStartNavigation.route,
            startDestination = Routes.OnBoardingScreen.route
        ){
            composable(
              route = Routes.OnBoardingScreen.route
            ){
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }



        navigation(
            route = Routes.AuthenticationNavigation.route,
            startDestination = Routes.AuthenticationNavigatorScreen.route
        ){
            composable(
                route = Routes.AuthenticationNavigatorScreen.route
            ){

                AuthenticationNavigator()
            }
        }


        navigation(
            route = Routes.SoundNavigation.route,
            startDestination = Routes.SoundNavigatorScreen.route
        ){
            composable(
                route = Routes.SoundNavigatorScreen.route
            ){
                SoundNavigator()
            }
        }
    }
}