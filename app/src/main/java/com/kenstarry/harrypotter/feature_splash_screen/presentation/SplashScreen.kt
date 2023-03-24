package com.kenstarry.harrypotter.feature_splash_screen.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kenstarry.harrypotter.R
import com.kenstarry.harrypotter.core.presentation.components.Lottie
import com.kenstarry.harrypotter.navigation.Direction
import com.kenstarry.harrypotter.navigation.NavConstants
import com.kenstarry.harrypotter.navigation.screens.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    mainNavHostController: NavHostController
) {

    val direction = Direction(mainNavHostController)

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Lottie(
            rawFile = R.raw.magician,
            isPlaying = true,
            iterations = Int.MAX_VALUE,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        )

        //  text
        Text(
            text = "Hogwarts",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
    }

    LaunchedEffect(true) {
        delay(3000)

        //  navigate to home screen
        direction.navigateToRoute(
            NavConstants.MAIN_ROUTE,
            NavConstants.SPLASH_ROUTE
        )
    }
}














