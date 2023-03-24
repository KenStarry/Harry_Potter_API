package com.kenstarry.harrypotter.feature_settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.harrypotter.core.presentation.util.Constants
import com.kenstarry.harrypotter.feature_houses.presentation.components.HousesTopBar
import com.kenstarry.harrypotter.feature_settings.presentation.components.SalutationSection
import com.kenstarry.harrypotter.feature_settings.presentation.components.themes_section.ThemesSection
import com.kenstarry.harrypotter.feature_settings.presentation.viewmodel.SettingsViewModel
import com.kenstarry.harrypotter.navigation.Direction
import com.kenstarry.harrypotter.navigation.NavConstants
import com.kenstarry.harrypotter.navigation.screens.BottomNavScreens
import com.kenstarry.harrypotter.navigation.screens.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navHostController: NavHostController,
    mainNavHostController: NavHostController,
) {

    val settingsVM: SettingsViewModel = hiltViewModel()
    val direction = Direction(navHostController)

    Scaffold(
        topBar = {
            HousesTopBar(
                title = "Settings",
                onBackPressed = {
                    direction.navigateToRoute(
                        BottomNavScreens.Home.route,
                        BottomNavScreens.Home.route
                    )
                }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.Start
            ) {

                ThemesSection(
                    settingsVM = settingsVM,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp)
                )

                SalutationSection()

            }
        }

    }

}





















