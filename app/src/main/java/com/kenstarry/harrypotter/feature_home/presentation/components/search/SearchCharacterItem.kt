package com.kenstarry.harrypotter.feature_home.presentation.components.search

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
fun SearchCharacterItem(
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(16.dp)
                .clickable { onCharacterClicked() },
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  image
            if (character.wizard) {

                //  wizard image
                WizardImage(
                    context = context,
                    uri = character.image.toUri(),
                    imageSize = 60.dp,
                    starSize = 16.dp
                )

            } else {
                CoilImage(
                    context = context,
                    imageUri = character.image.toUri(),
                    placeholder = R.drawable.profile,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(60.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                //  display character name
                Text(
                    text = character.name,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                )

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


}























