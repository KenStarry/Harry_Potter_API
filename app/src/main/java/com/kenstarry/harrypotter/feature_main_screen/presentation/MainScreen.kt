package com.kenstarry.harrypotter.feature_main_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.kenstarry.harrypotter.navigation.graphs.inner_graphs.MainInnerGraph

@Composable
fun MainScreen(
    navHostController: NavHostController
) {

    val navController = rememberNavController()

    var showAppIntro by remember {
        mutableStateOf(false)
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showAppIntro,
        onShowCaseCompleted = { showAppIntro = false }
    ) {

        Scaffold(
            bottomBar = {}
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
                    navHostController = navController
                )
            }

        }

    }

}