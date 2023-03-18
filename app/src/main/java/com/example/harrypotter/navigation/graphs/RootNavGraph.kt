package com.example.harrypotter.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.harrypotter.navigation.NavConstants

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = NavConstants.MAIN_ROUTE
    ) {

        mainNavGraph(navHostController = navHostController)

    }
}