package com.ozge.restaurantz.ui.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ozge.restaurantz.ui.theme.statusBarColor

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val restaurantsPagingData = homeViewModel.restaurants.collectAsLazyPagingItems()

    rememberSystemUiController().apply {
        setStatusBarColor(
            color = MaterialTheme.colors.statusBarColor
        )
    }

    Scaffold(
        topBar = {
            HomeTopBar()
        }) {
        ListContent(
            restaurants = restaurantsPagingData,
            navHostController = navHostController
        )
    }

}