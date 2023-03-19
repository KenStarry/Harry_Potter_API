package com.kenstarry.harrypotter.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kenstarry.harrypotter.core.domain.model.CharacterModel

@Composable
fun CharacterItem(
    character: CharacterModel
) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(
                width = 200.dp,
                height = 200.dp
            )
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(16.dp)
    ) {

    }

}























