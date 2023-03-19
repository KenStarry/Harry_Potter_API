package com.kenstarry.harrypotter.feature_home.presentation.components

import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.HomeMini
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.House
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.palette.graphics.Palette
import com.kenstarry.harrypotter.R
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.presentation.components.CoilImage
import com.kenstarry.harrypotter.core.presentation.components.PillBtn

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

            CoilImage(
                context = context,
                imageUri = character.image.toUri(),
                placeholder = R.drawable.ic_launcher_background,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
            )

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
                icon = Icons.Outlined.House,
                horizontalSpacing = 8.dp,
                verticalSpacing = 8.dp,
                onClick = onHouseClicked
            )

        }

    }


}























