package com.kenstarry.harrypotter.feature_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notes
import androidx.compose.material.icons.outlined.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.navigation.Direction

@Composable
fun HogwartsStaffSection(
    allHogwartsStaff: List<CharacterModel>,
    direction: Direction,
    onCharacterClicked: (character: CharacterModel) -> Unit,
    onHouseClicked: (house: String) -> Unit,
    onSeeAll: () -> Unit
) {

    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        SectionTitle(
            title = "Hogwarts Staff",
            icon = Icons.Outlined.School,
            onSeeAll = onSeeAll
        )

        LazyRow(
            content = {
                items(allHogwartsStaff) {
                    CharacterItemAlt(
                        character = it,
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

















