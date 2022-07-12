package com.ozge.restaurantz.data.ui.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.ozge.restaurantz.ui.screens.detail.DetailsContent
import com.ozge.restaurantz.utils.restaurantUIModel1
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterialApi
class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun DetailScreen_WithData_Display() {
        with(composeTestRule) {
            this.setContent {
                DetailsContent(
                    navHostController = rememberNavController(),
                    restaurantUIModel = restaurantUIModel1,
                    colors = hashMapOf()
                )
            }
            onNodeWithText("Golden Pub").assertIsDisplayed()
            onNodeWithText("Culver’s Restaurant was founded by the Culver family in 1984, which eventually branched out to more than 300 franchised restaurants all over the US. Culver’s is well-known for its ButterBurger, which made the restaurant extremely famous. They also have other items which include salads, sandwiches, desserts, etc.").assertIsDisplayed()
            onNodeWithContentDescription("RestaurantImage").assertIsDisplayed()
        }
    }

}