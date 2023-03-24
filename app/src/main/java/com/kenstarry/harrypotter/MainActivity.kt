package com.kenstarry.harrypotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.kenstarry.harrypotter.core.domain.model.BottomSheetEvents
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.presentation.components.BottomSheet
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_home.presentation.components.DetailBottomSheet
import com.kenstarry.harrypotter.feature_home.presentation.util.HomeConstants
import com.kenstarry.harrypotter.feature_settings.presentation.util.SettingsConstants
import com.kenstarry.harrypotter.feature_settings.presentation.viewmodel.SettingsViewModel
import com.kenstarry.harrypotter.navigation.graphs.RootNavGraph
import com.kenstarry.harrypotter.ui.theme.HarryPotterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            
            val settingsViewModel: SettingsViewModel = hiltViewModel()

            //  setting theme using datastore
            HarryPotterTheme(
                darkTheme = when (settingsViewModel.themeFlow.collectAsState(initial = "").value) {
                    SettingsConstants.themeOptions[0].title -> {
                        //  dark theme enabled
                        true
                    }
                    SettingsConstants.themeOptions[0].title -> {
                        //  dark theme disabled
                        false
                    }
                    SettingsConstants.themeOptions[0].title -> {
                        //  follow system enabled
                        isSystemInDarkTheme()
                    }
                    else -> {
                        isSystemInDarkTheme()
                    }
                }
            ) {

                val coreVM: CoreViewModel = hiltViewModel()

                BottomSheet(
                    sheetBackground = MaterialTheme.colorScheme.onPrimary,
                    topStartRadius = 0.dp,
                    topEndRadius = 0.dp,
                    sheetContent = { state, scope ->

                        when (coreVM.bottomSheetContent.value) {

                            HomeConstants.DETAILS_BOTTOM_SHEET -> {
                                DetailBottomSheet(
                                    character = coreVM.bottomSheetData.value as CharacterModel,
                                    onBackPressed = {
                                        //  close bottomsheet
                                        coreVM.onBottomSheetEvent(BottomSheetEvents.CloseBottomSheet(
                                            state = state,
                                            scope = scope
                                        ))
                                    }
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
