package com.kenstarry.harrypotter.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kenstarry.harrypotter.feature_main_screen.presentation.MainScreen
import com.kenstarry.harrypotter.navigation.NavConstants
import com.kenstarry.harrypotter.navigation.screens.Screens

fun NavGraphBuilder.mainNavGraph(
    navHostController: NavHostController
) {

    //  main screen
    navigation(
        startDestination = Screens.Main.route,
        route = NavConstants.MAIN_ROUTE
    ) {

        composable(route = Screens.Main.route) {
            MainScreen(navHostController)
        }

    }
}