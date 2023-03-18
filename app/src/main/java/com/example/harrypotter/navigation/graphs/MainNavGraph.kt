package com.example.harrypotter.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.harrypotter.feature_main_screen.MainScreen
import com.example.harrypotter.navigation.NavConstants
import com.example.harrypotter.navigation.screens.Screens

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