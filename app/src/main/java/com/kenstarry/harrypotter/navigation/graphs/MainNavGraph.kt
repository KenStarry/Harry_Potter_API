package com.kenstarry.harrypotter.navigation.graphs

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_categories.presentation.CategoriesScreen
import com.kenstarry.harrypotter.feature_detail.presentation.DetailScreen
import com.kenstarry.harrypotter.feature_main_screen.presentation.MainScreen
import com.kenstarry.harrypotter.feature_splash_screen.presentation.SplashScreen
import com.kenstarry.harrypotter.navigation.NavConstants
import com.kenstarry.harrypotter.navigation.screens.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.mainNavGraph(
    navHostController: NavHostController,
    coreViewModel: CoreViewModel,
    modalSheetState: ModalBottomSheetState,
    scope: CoroutineScope
) {

    //  main screen
    navigation(
        startDestination = Screens.Main.route,
        route = NavConstants.MAIN_ROUTE
    ) {

        composable(route = Screens.Splash.route) {
            SplashScreen(
                navHostController
            )
        }

        composable(route = Screens.Main.route) {
            MainScreen(
                navHostController,
                coreVM = coreViewModel,
                state = modalSheetState,
                scope = scope
            )
        }

        composable(
            route = Screens.Category.route,
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            CategoriesScreen(
                mainNavHostController = navHostController,
                categoryContent = it.arguments?.getString("category") ?: "no category",
                coreVM = coreViewModel,
                state = modalSheetState,
                scope = scope
            )
        }

        composable(
            route = Screens.Detail.route,
            arguments = listOf(
                navArgument("model") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(
                navHostController = navHostController,
                characterModel = null
            )

//            Json.decodeFromString(
//                CharacterModel.serializer(),
//                it.arguments?.getString("model") ?: ""
//            )
        }

    }
}