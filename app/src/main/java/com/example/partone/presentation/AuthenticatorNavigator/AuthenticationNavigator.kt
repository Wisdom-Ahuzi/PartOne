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
import com.example.partone.presentation.soundNavigator.SoundNavigator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun AuthenticationNavigator(){

    val navController = rememberNavController()


//    val user = Firebase.auth.currentUser
//    if (user != null) {
//        navController.navigate(Routes.SoundNavigatorScreen.route)
//        // User is signed in
//    }


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

        composable(route = Routes.SoundNavigatorScreen.route){
            SoundNavigator()
        }

    }
}