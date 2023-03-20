package com.kenstarry.harrypotter.feature_detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.kenstarry.harrypotter.feature_detail.presentation.components.DetailTopBar
import com.kenstarry.harrypotter.feature_home.presentation.components.HomeTopBar
import com.kenstarry.harrypotter.navigation.Direction

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    characterId: String
) {


    val direction = Direction(navHostController)
    var showAppIntro by remember {
        mutableStateOf(false)
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showAppIntro,
        onShowCaseCompleted = { showAppIntro = false }
    ) {

        Scaffold(
            topBar = {
                DetailTopBar(title = characterId) {
                    direction.navigateUp()
                }
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
                        .padding(16.dp)
                ) {

                }
            }
        }
    }


}