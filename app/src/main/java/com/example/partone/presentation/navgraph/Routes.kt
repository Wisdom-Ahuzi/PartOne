package com.example.partone.presentation.navgraph

sealed class Routes(
    val route:String
){
    object OnBoardingScreen : Routes( route = "onBoardingScreen")
    object SignUpScreen : Routes(route = "signUpScreen")
    object LoginScreen : Routes(route = "loginScreen")
    object HomeScreen : Routes(route = "homeScreen")
    object FilesScreen : Routes(route = "filesScreen")
    object ProfileScreen : Routes(route = "profileScreen")
    object AppStartNavigation:Routes(route = "appStartNavigation")
    object SoundNavigation : Routes( route = "soundNavigation")
    object SoundNavigatorScreen : Routes(route =  "soundNavigator")

    object AuthenticationNavigation : Routes( route = "authenticationNavigation")

    object AuthenticationNavigatorScreen : Routes(route = "authenticationAuthenticator")

}
