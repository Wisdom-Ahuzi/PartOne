package com.example.partone.presentation.AuthenticationNavigator

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.partone.presentation.files.FilesScreen
import com.example.partone.presentation.home.HomeScreen
import com.example.partone.presentation.login.LoginScreen
import com.example.partone.presentation.navgraph.Routes
import com.example.partone.presentation.profile.ProfileScreen
import com.example.partone.presentation.signup.SignupScreen
import com.example.partone.presentation.signup.SignupViewModel

@Composable
fun AuthenticationNavigator(){

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Routes.SignUpScreen.route,
    ){
        composable(route = Routes.SignUpScreen.route){
            SignupScreen(navController)
        }
        composable(route = Routes.LoginScreen.route){
            LoginScreen(navController)
        }

    }
}