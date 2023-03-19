package com.kenstarry.harrypotter.feature_home.presentation.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.harrypotter.R
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.presentation.components.CoilImage

@Composable
fun CharacterItem(
    character: CharacterModel
) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(
                width = 200.dp,
                height = 200.dp
            )
            .background(MaterialTheme.colorScheme.onPrimary),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            CoilImage(
                context = context,
                imageUri = character.image.toUri(),
                placeholder = R.drawable.ic_launcher_background,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
            )

            Text(
                text = character.name,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
            )

            //  pill button

        }

    }

}























