package com.kenstarry.harrypotter.navigation.graphs.inner_graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kenstarry.harrypotter.feature_characters.presentation.CharactersScreen
import com.kenstarry.harrypotter.feature_home.presentation.HomeScreen
import com.kenstarry.harrypotter.feature_settings.presentation.SettingsScreen
import com.kenstarry.harrypotter.navigation.screens.BottomSheetScreens

@Composable
fun MainInnerGraph(
    mainNavHostController: NavHostController,
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = BottomSheetScreens.Home.route
    ) {

        composable(route = BottomSheetScreens.Home.route) {
            HomeScreen(navHostController)
        }

        composable(route = BottomSheetScreens.Characters.route) {
            CharactersScreen(navHostController)
        }

        composable(route = BottomSheetScreens.Settings.route) {
            SettingsScreen(navHostController)
        }

    }

}