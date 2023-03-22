package com.kenstarry.harrypotter.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.House
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.kenstarry.harrypotter.core.presentation.components.PillBtn
import com.kenstarry.harrypotter.core.presentation.components.WizardImage

@Composable
fun CharacterItem(
    character: CharacterModel,
    onCharacterClicked: () -> Unit,
    onHouseClicked: () -> Unit
) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.onPrimary),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .clickable { onCharacterClicked() }
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (character.wizard) {

                //  wizard image
                WizardImage(
                    context = context,
                    uri = character.image.toUri(),
                    imageSize = 80.dp,
                    starSize = 25.dp
                )

            } else {
                CoilImage(
                    context = context,
                    imageUri = character.image.toUri(),
                    placeholder = R.drawable.profile,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = character.name,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //  pill button
            PillBtn(
                title = character.house,
                startIcon = Icons.Outlined.House,
                horizontalPadding = 8.dp,
                verticalPadding = 8.dp,
                onClick = onHouseClicked
            )

        }

    }


}























