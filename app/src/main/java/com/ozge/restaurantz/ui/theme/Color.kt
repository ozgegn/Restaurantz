package com.ozge.restaurantz.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)

val Colors.statusBarColor
    @Composable
    get() = if (isLight) Purple700 else Color.Black

val Colors.topAppBarContentColor
    @Composable
    get() = if (isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor
    @Composable
    get() = if (isLight) Purple700 else Color.Black

val Colors.titleTextColor
    @Composable
    get() = if (isLight) Purple700 else Color.LightGray

val Colors.warningTintColor
    @Composable
    get() = if (isLight) DarkGray else LightGray