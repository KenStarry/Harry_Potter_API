package com.kenstarry.harrypotter.navigation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.HomeWork
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

    object Houses : BottomNavScreens(
        route = NavConstants.HOUSES_SCREEN_ROUTE,
        icon = Icons.Outlined.HomeWork,
        title = "Houses"
    )

    object Settings : BottomNavScreens(
        route = NavConstants.SETTINGS_SCREEN_ROUTE,
        icon = Icons.Outlined.Settings,
        title = "Settings"
    )
}
