package com.ozge.restaurantz.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.ozge.restaurantz.R
import com.ozge.restaurantz.domain.model.RestaurantUIModel
import com.ozge.restaurantz.navigation.Screen
import com.ozge.restaurantz.ui.theme.titleTextColor
import com.ozge.restaurantz.utils.LARGE_PADDING
import com.ozge.restaurantz.utils.MEDIUM_PADDING
import com.ozge.restaurantz.utils.RESTAURANT_ITEM_HEIGHT

@Composable
fun ListContent(
    restaurants: List<RestaurantUIModel>,
    navHostController: NavHostController
) {
    LazyColumn {
        items(restaurants) {
            ListItem(restaurantUIModel = it, navHostController = navHostController)
        }
    }
}

@Composable
fun ListItem(
    restaurantUIModel: RestaurantUIModel,
    navHostController: NavHostController
) {
    val painter = rememberAsyncImagePainter(
        model = restaurantUIModel.logo,
        placeholder = painterResource(id = R.drawable.placeholder),
        error = painterResource(id = R.drawable.placeholder)
    )

    Box(
        modifier = Modifier
            .height(RESTAURANT_ITEM_HEIGHT)
            .fillMaxWidth()
            .clickable { navHostController.navigate(Screen.Detail.passId(restaurantUIModel.id)) },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                painter = painter,
                contentDescription = "Restaurant Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(bottomStart = LARGE_PADDING, bottomEnd = LARGE_PADDING)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MEDIUM_PADDING)
            ) {
                Text(
                    text = restaurantUIModel.name,
                    color = MaterialTheme.colors.titleTextColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = restaurantUIModel.description,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MEDIUM_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Address Icon",
                        tint = Color.LightGray.copy(alpha = ContentAlpha.medium)
                    )
                    Text(
                        text = restaurantUIModel.address,
                        color = Color.LightGray.copy(alpha = ContentAlpha.medium),
                        fontSize = MaterialTheme.typography.subtitle2.fontSize,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ListItemPreview() {
    ListItem(
        navHostController = rememberNavController(),
        restaurantUIModel = RestaurantUIModel(
            id = 0,
            uid = "",
            name = "Green Grill & Tap",
            description = "We are committed to using the finest ingredients in our recipes. No food leaves our kitchen that we ourselves would not eat.",
            type = "Vegetarian",
            review = "The counter is on the left side, and so is the menu. It can get pretty busy with 30 min wait times. I recommend checking the website and see how busy their store is.",
            logo = "https://loremflickr.com/500/500/restaurant",
            phoneNumber = "5555555555",
            address = "46668 Caryl Ridges, West Denaberg, OH 60320-0890",
            hours = emptyList()
        )
    )
}