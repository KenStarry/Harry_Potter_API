package com.kenstarry.harrypotter.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun Lottie(
    modifier: Modifier = Modifier,
    rawFile: Int,
    isPlaying: Boolean = false,
    iterations: Int = 1
) {

    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(rawFile)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying,
        iterations = iterations
    )

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier
    )
}