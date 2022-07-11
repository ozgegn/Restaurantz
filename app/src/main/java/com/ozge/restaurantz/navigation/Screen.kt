package com.ozge.restaurantz.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Detail : Screen("detail_screen/{id}") {
        fun passId(id: Int): String {
            return "detail_screen/$id"
        }
    }
}