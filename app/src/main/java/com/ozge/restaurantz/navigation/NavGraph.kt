package com.ozge.restaurantz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ozge.restaurantz.utils.NavConstants


@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {

        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(NavConstants.KEY_RESTAURANT_ID) {
                type = NavType.IntType
            })
        ) {

        }
    }
}