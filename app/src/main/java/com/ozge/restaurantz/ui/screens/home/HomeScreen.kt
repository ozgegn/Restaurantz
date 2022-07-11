package com.ozge.restaurantz.ui.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ozge.restaurantz.domain.model.Resource
import com.ozge.restaurantz.domain.model.RestaurantUIModel
import com.ozge.restaurantz.ui.components.EmptyScreen
import com.ozge.restaurantz.ui.theme.statusBarColor

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val restaurantsResource by homeViewModel.restaurants.observeAsState()

    rememberSystemUiController().apply {
        setStatusBarColor(
            color = MaterialTheme.colors.statusBarColor
        )
    }

    Scaffold(
        topBar = {
            HomeTopBar()
        }) {
        when (restaurantsResource) {
            is Resource.Success -> {
                ListContent(
                    restaurants = (restaurantsResource as Resource.Success<List<RestaurantUIModel>>).data,
                    navHostController = navHostController
                )
            }
            is Resource.Loading -> {

            }
            else -> {
                EmptyScreen(
                    error = (restaurantsResource as? Resource.Failure)?.error
                ) {
                    homeViewModel.getAllRestaurants()
                }
            }
        }
    }

}