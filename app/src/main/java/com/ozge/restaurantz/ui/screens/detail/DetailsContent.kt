package com.ozge.restaurantz.ui.screens.detail

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ozge.restaurantz.R
import com.ozge.restaurantz.domain.model.RestaurantUIModel
import com.ozge.restaurantz.ui.theme.titleTextColor
import com.ozge.restaurantz.utils.CLOSE_ICON_SIZE
import com.ozge.restaurantz.utils.EXPANDED_RADIUS_LEVEL
import com.ozge.restaurantz.utils.EXTRA_LARGE_PADDING
import com.ozge.restaurantz.utils.MEDIUM_PADDING
import com.ozge.restaurantz.utils.MINIMUM_BACKGROUND_IMAGE
import com.ozge.restaurantz.utils.MIN_SHEET_HEIGHT
import com.ozge.restaurantz.utils.REVIEW_ICON_SIZE
import com.ozge.restaurantz.utils.SMALL_PADDING

@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navHostController: NavHostController,
    restaurantUIModel: RestaurantUIModel?,
    colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#000000") }

    LaunchedEffect(key1 = restaurantUIModel) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    rememberSystemUiController().apply {
        setStatusBarColor(
            color = Color(parseColor(darkVibrant))
        )
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            restaurantUIModel?.let {
                BottomSheetContent(
                    restaurantUIModel = it,
                    sheetBackgroundColor = Color(
                        parseColor(darkVibrant)
                    ),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        content = {
            BackgroundContent(
                image = restaurantUIModel?.logo.orEmpty(),
                imageFraction = currentSheetFraction,
                backgroundColor = Color(parseColor(darkVibrant))
            ) {
                navHostController.popBackStack()
            }
        }
    )
}

@Composable
fun BackgroundContent(
    image: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val painter = rememberAsyncImagePainter(
        model = image,
        placeholder = painterResource(id = R.drawable.placeholder)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            painter = painter, contentDescription = "Restaurant Image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(imageFraction + MINIMUM_BACKGROUND_IMAGE)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(CLOSE_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Restaurant Image",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    restaurantUIModel: RestaurantUIModel,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleTextColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(MEDIUM_PADDING)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING),
            text = restaurantUIModel.name,
            color = contentColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = restaurantUIModel.description,
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING, top = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(REVIEW_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.comment),
                contentDescription = "Review Icon",
                tint = contentColor
            )
            Text(
                modifier = Modifier.weight(8f),
                text = restaurantUIModel.review,
                color = contentColor,
                fontSize = MaterialTheme.typography.subtitle2.fontSize,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }

    }

@Composable
@Preview
fun BottomSheetContentPreview() {
    BottomSheetContent(
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