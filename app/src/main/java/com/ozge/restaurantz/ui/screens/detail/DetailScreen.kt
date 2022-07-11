package com.ozge.restaurantz.ui.screens.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ozge.restaurantz.utils.PaletteGenerator.convertImageUrlIntoBitmap
import com.ozge.restaurantz.utils.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {

    val colorPalette by detailViewModel.colorPalette
    val restaurantDetail by detailViewModel.restaurantDetail.collectAsState()

    if (colorPalette.isEmpty()) {
        detailViewModel.generateColorPalette()
    } else {
        DetailsContent(
            navHostController = navHostController,
            restaurantUIModel = restaurantDetail,
            colors = colorPalette
        )
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    restaurantDetail?.logo?.let {
                        val bitmap = convertImageUrlIntoBitmap(
                            url = it,
                            context = context
                        )
                        if (bitmap != null) {
                            detailViewModel.setColorPalette(
                                colors = extractColorsFromBitmap(bitmap)
                            )
                        }
                    }
                }
            }
        }
    }
}