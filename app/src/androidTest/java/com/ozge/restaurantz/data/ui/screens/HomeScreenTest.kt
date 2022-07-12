package com.ozge.restaurantz.data.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.ozge.restaurantz.ui.screens.home.ListItem
import com.ozge.restaurantz.utils.restaurantUIModel1
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ListItem_WithData_ExpectShowingData() {
        composeTestRule.setContent {
            ListItem(
                restaurantUIModel = restaurantUIModel1,
                navHostController = rememberNavController()
            )
        }
        composeTestRule.onNodeWithContentDescription("RestaurantBackgroundImage")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Golden Pub")
            .assertIsDisplayed()
    }
}