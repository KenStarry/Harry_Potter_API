package com.kenstarry.harrypotter.navigation.screens

import com.kenstarry.harrypotter.navigation.NavConstants

sealed class Screens(val route: String) {

    object Splash : Screens(route = NavConstants.SPLASH_SCREEN_ROUTE)

    object Main : Screens(route = NavConstants.MAIN_SCREEN_ROUTE)

    object Detail : Screens(route = "${NavConstants.DETAIL_SCREEN_ROUTE}/{model}") {
        fun passCharacterModel(characterModelString: String) =
            "${NavConstants.DETAIL_SCREEN_ROUTE}/$characterModelString"
    }

    object Category : Screens(route = "${NavConstants.CATEGORY_SCREEN_ROUTE}/{category}") {
        fun passCategory(category: String) =
            "${NavConstants.CATEGORY_SCREEN_ROUTE}/$category"
    }
}
