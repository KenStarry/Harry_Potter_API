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
    textColor: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    containerColor: Color = MaterialTheme.colorScheme.onSecondary,
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 16.dp,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(48.dp))
            .wrapContentSize()
            .background(containerColor)
            .clickable { onClick() }
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        startIcon?.let {
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
            color = textColor
        )

        endIcon?.let {
            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = it,
                contentDescription = "Pill icon",
                tint = MaterialTheme.colorScheme.primary
            )
        }

    }
}