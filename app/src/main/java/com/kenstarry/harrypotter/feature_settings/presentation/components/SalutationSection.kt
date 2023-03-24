package com.kenstarry.harrypotter.feature_settings.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.harrypotter.BuildConfig

@Composable
fun SalutationSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Favourites",
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Made with Love, By Kenstarry",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
        )

        //  APP VERSION
        Text(
            text = "App version : ${BuildConfig.VERSION_NAME}",
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
        )

    }
}