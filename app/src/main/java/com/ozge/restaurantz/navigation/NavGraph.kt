package com.ozge.restaurantz.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ozge.restaurantz.ui.screens.detail.DetailScreen
import com.ozge.restaurantz.ui.screens.home.HomeScreen
import com.ozge.restaurantz.utils.NavConstants


@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(NavConstants.KEY_RESTAURANT_ID) {
                type = NavType.IntType
            })
        ) {
            DetailScreen(navHostController = navHostController)
        }
    }
}