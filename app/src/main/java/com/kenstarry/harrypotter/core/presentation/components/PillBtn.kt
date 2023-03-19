package com.kenstarry.harrypotter.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PillBtn(
    title: String,
    icon: ImageVector? = null,
    horizontalSpacing: Dp = 16.dp,
    verticalSpacing: Dp = 16.dp,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(32.dp))
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.tertiary)
            .clickable { onClick() }
            .padding(
                horizontal = horizontalSpacing,
                vertical = verticalSpacing
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = "Pill icon",
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = title,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

    }
}