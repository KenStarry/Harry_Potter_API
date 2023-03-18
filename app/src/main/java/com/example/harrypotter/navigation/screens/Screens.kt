package com.example.harrypotter.navigation.screens

import com.example.harrypotter.navigation.NavConstants

sealed class Screens(val route: String) {

    object Main : Screens(route = NavConstants.MAIN_SCREEN_ROUTE)
}
