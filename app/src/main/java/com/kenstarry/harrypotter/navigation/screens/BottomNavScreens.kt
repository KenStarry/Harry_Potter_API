package com.kenstarry.harrypotter.navigation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.kenstarry.harrypotter.navigation.NavConstants

sealed class BottomNavScreens(
    val route: String,
    val icon: ImageVector,
    val title: String
) {

    object Home : BottomNavScreens(
        route = NavConstants.HOME_SCREEN_ROUTE,
        icon = Icons.Outlined.Home,
        title = "Home"
    )

    object Characters : BottomNavScreens(
        route = NavConstants.CHARACTER_SCREEN_ROUTE,
        icon = Icons.Outlined.Movie,
        title = "Characters"
    )

    object Settings : BottomNavScreens(
        route = NavConstants.SETTINGS_SCREEN_ROUTE,
        icon = Icons.Outlined.Settings,
        title = "Settings"
    )
}
