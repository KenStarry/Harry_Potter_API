package com.kenstarry.harrypotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.kenstarry.harrypotter.core.domain.model.BottomSheetEvents
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.presentation.components.BottomSheet
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_home.presentation.components.DetailBottomSheet
import com.kenstarry.harrypotter.feature_home.presentation.util.HomeConstants
import com.kenstarry.harrypotter.feature_main_screen.presentation.components.bottom_sheet.MainBottomNav
import com.kenstarry.harrypotter.navigation.graphs.RootNavGraph
import com.kenstarry.harrypotter.navigation.graphs.inner_graphs.MainInnerGraph
import com.kenstarry.harrypotter.ui.theme.HarryPotterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarryPotterTheme {

                val coreVM: CoreViewModel = hiltViewModel()

                BottomSheet(
                    sheetBackground = MaterialTheme.colorScheme.onPrimary,
                    topStartRadius = 0.dp,
                    topEndRadius = 0.dp,
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

                        RootNavGraph(
                            navHostController = rememberNavController(),
                            coreViewModel = coreVM,
                            modalSheetState = state,
                            scope = scope
                        )

                    },
                    closeBottomSheet = { state, scope ->
                        coreVM.onBottomSheetEvent(BottomSheetEvents.CloseBottomSheet(state, scope))
                    }
                )
            }
        }
    }
}
