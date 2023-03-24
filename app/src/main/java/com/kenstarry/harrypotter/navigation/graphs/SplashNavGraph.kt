package com.kenstarry.harrypotter.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kenstarry.harrypotter.feature_splash_screen.presentation.SplashScreen
import com.kenstarry.harrypotter.navigation.NavConstants
import com.kenstarry.harrypotter.navigation.screens.Screens

fun NavGraphBuilder.splashNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Screens.Splash.route,
        route = NavConstants.SPLASH_ROUTE
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(
                navHostController
            )
        }
    }
}