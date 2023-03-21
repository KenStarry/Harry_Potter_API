package com.kenstarry.harrypotter.feature_main_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.kenstarry.harrypotter.core.domain.model.BottomSheetEvents
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.presentation.components.BottomSheet
import com.kenstarry.harrypotter.core.presentation.components.WizardsShimmer
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_home.presentation.components.DetailBottomSheet
import com.kenstarry.harrypotter.feature_home.presentation.components.HogwartsStaffSection
import com.kenstarry.harrypotter.feature_home.presentation.components.HomeTopBar
import com.kenstarry.harrypotter.feature_home.presentation.components.WizardsSection
import com.kenstarry.harrypotter.feature_home.presentation.util.HomeConstants
import com.kenstarry.harrypotter.feature_main_screen.presentation.components.bottom_sheet.MainBottomNav
import com.kenstarry.harrypotter.navigation.graphs.inner_graphs.MainInnerGraph

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navHostController: NavHostController
) {

    val navController = rememberNavController()
    val coreVM: CoreViewModel = hiltViewModel()

    var showAppIntro by remember {
        mutableStateOf(false)
    }

    BottomSheet(
        sheetBackground = MaterialTheme.colorScheme.onPrimary,
        sheetContent = { state, scope ->

            when (coreVM.bottomSheetContent.value) {

                HomeConstants.DETAILS_BOTTOM_SHEET -> {
                    DetailBottomSheet(
                        character = coreVM.bottomSheetData.value as CharacterModel
                    )
                }
            }
        },
        sheetScope = { state, scope ->

            IntroShowCaseScaffold(
                showIntroShowCase = showAppIntro,
                onShowCaseCompleted = { showAppIntro = false }
            ) {

                Scaffold(
                    bottomBar = {
                        MainBottomNav(navHostController = navController)
                    }
                ) { contentPadding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .padding(contentPadding)
                    ) {
                        //  navgraph for the bottom sheets
                        MainInnerGraph(
                            mainNavHostController = navHostController,
                            navHostController = navController,
                            modalSheetState = state,
                            scope = scope
                        )
                    }

                }

            }

        },
        closeBottomSheet = { state, scope ->
            coreVM.onBottomSheetEvent(BottomSheetEvents.CloseBottomSheet(state, scope))
        }
    )

}