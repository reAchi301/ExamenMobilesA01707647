package com.app.examenmobilesa01707647.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.examenmobilesa01707647.presentation.screens.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")

}

@Composable
fun CovidNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {

        composable(route = Screen.Home.route) {
            HomeScreen()
        }
    }
}