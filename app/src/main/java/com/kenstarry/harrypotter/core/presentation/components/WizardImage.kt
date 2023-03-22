package com.kenstarry.harrypotter.core.presentation.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kenstarry.harrypotter.R

@Composable
fun WizardImage(
    context: Context,
    imageSize: Dp = 120.dp,
    starSize: Dp = 30.dp,
    starIconSize: Dp = 16.dp,
    uri: Uri
) {

    //  wizard image
    Box(
        modifier = Modifier
            .size(size = imageSize)
    ) {

        CoilImage(
            context = context,
            imageUri = uri,
            placeholder = R.drawable.profile,
            modifier = Modifier
                .clip(CircleShape)
                .size(size = imageSize)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(starSize)
                    .background(MaterialTheme.colorScheme.onPrimary),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Star",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(starIconSize)
                )

            }

        }

    }

}