package com.kenstarry.harrypotter.navigation.screens

import com.kenstarry.harrypotter.navigation.NavConstants

sealed class Screens(val route: String) {

    object Main : Screens(route = NavConstants.MAIN_SCREEN_ROUTE)

    object Detail : Screens(route = "${NavConstants.DETAIL_SCREEN_ROUTE}/{id}") {
        fun passCharacterId(id: String) = "${NavConstants.DETAIL_SCREEN_ROUTE}/$id"
    }
}
