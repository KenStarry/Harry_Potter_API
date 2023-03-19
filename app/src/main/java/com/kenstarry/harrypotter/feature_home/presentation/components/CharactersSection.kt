package com.kenstarry.harrypotter.feature_home.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kenstarry.harrypotter.core.domain.model.CharacterModel

@Composable
fun CharactersSection(
    modifier: Modifier = Modifier,
    allCharacters: List<CharacterModel>
) {

    val state = rememberLazyListState()

    LazyColumn(
        content = {
            items(allCharacters) { character ->

            }
        },
        state = state,
        modifier = modifier
    )

}