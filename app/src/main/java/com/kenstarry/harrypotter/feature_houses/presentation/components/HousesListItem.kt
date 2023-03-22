package com.kenstarry.harrypotter.feature_houses.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.feature_home.presentation.components.CharacterItemAlt

@Composable
fun HousesListItem(
    houseName: String,
    charactersInHouse: List<CharacterModel>,
    onCharacterClicked: (character: CharacterModel) -> Unit,
    onHouseClicked: (house: String) -> Unit,
) {

    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = houseName,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )

        LazyRow(
            content = {
                items(charactersInHouse) {
                    CharacterItemAlt(
                        character = it,
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        onCharacterClicked = { onCharacterClicked(it) },
                        onHouseClicked = { onHouseClicked(it.house) }
                    )
                }
            },
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

    }

}