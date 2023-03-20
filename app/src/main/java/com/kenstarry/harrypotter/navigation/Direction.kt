package com.kenstarry.harrypotter.navigation

import androidx.navigation.NavHostController

class Direction(
    navHostController: NavHostController
) {

    val navigateToRoute: (
        route: String,
        popRoute: String?
    ) -> Unit = {route, popRoute ->

         if (popRoute != null) {
             navHostController.navigate(route = route) {
                 popUpTo(popRoute)
                 launchSingleTop = true
             }
         } else
             navHostController.navigate(route = route)
    }

    val navigateUp: () -> Unit = {
        navHostController.navigateUp()
    }
}