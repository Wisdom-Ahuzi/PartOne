package com.example.partone.presentation.soundNavigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.partone.R
import com.example.partone.presentation.files.FilesScreen
import com.example.partone.presentation.home.HomeScreen
import com.example.partone.presentation.navgraph.Routes
import com.example.partone.presentation.profile.ProfileScreen
import com.example.partone.presentation.soundNavigator.components.BottomNavigationItem
import com.example.partone.presentation.soundNavigator.components.SoundBottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundNavigator(){

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text="Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text="Files"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text="Profile"),
        )
    }

    val navController = rememberNavController()
    val backstackStake = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when(backstackStake?.destination?.route){
        Routes.HomeScreen.route ->0
        Routes.FilesScreen.route -> 1
        Routes.ProfileScreen.route -> 2
        else -> 0
    }
    
    Scaffold( 
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            SoundBottomNavigation(
                items = bottomNavigationItem,
                selected = selectedItem,
                onItemClick = {index ->
                    when(index){
                        0 -> navigateToTab(
                            navController = navController,
                            route = Routes.HomeScreen.route
                        )
                        1 -> navigateToTab(
                            navController = navController,
                            route = Routes.FilesScreen.route
                        )
                        2 -> navigateToTab(
                            navController = navController,
                            route = Routes.ProfileScreen.route
                        )
                    }
                }
            )
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Routes.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ){
            composable(route = Routes.HomeScreen.route){
                HomeScreen()
            }
            composable(route = Routes.FilesScreen.route){
                FilesScreen()
            }
            composable(route = Routes.ProfileScreen.route){
                ProfileScreen()
            }
        }
    }
}

private  fun navigateToTab(navController: NavController, route:String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let {homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true

        }
    }
}