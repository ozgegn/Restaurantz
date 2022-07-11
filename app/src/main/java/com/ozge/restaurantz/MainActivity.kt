package com.ozge.restaurantz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ozge.restaurantz.navigation.SetupNavGraph
import com.ozge.restaurantz.ui.theme.RestaurantzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantzTheme {
                val navGraph = rememberNavController()
                SetupNavGraph(navHostController = navGraph)
            }
        }
    }
}