package com.kenstarry.harrypotter.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.harrypotter.R
import com.kenstarry.harrypotter.core.presentation.components.Lottie

@Composable
fun ErrorMessage(
    message: String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Lottie(
            rawFile = R.raw.no_internet,
            isPlaying = true,
            iterations = Int.MAX_VALUE,
            modifier = Modifier
                .fillMaxSize(0.7f)
        )

        //  message
        Text(
            text = message,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.secondary
        )

    }
}