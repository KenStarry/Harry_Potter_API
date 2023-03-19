package com.kenstarry.harrypotter.navigation.screens

import com.kenstarry.harrypotter.navigation.NavConstants

sealed class Screens(val route: String) {

    object Main : Screens(route = NavConstants.MAIN_SCREEN_ROUTE)
}
