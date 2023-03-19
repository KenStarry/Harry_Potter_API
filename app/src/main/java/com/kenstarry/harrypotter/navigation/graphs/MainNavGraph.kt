package com.kenstarry.harrypotter.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.kenstarry.harrypotter.feature_detail.presentation.DetailScreen
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

        composable(
            route = Screens.Detail.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(
                navHostController = navHostController,
                characterId = it.arguments?.getString("id") ?: ""
            )
        }

    }
}