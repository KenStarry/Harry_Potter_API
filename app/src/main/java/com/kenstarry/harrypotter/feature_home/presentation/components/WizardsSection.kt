package com.kenstarry.harrypotter.feature_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.navigation.Direction

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WizardsSection(
    modifier: Modifier = Modifier,
    direction: Direction,
    allWizards: List<CharacterModel>,
    onWizardClicked: (character: CharacterModel) -> Unit,
    onHouseClicked: (house: String) -> Unit
) {

    val listState = rememberLazyListState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Icon(
            imageVector = Icons.Outlined.Star,
            contentDescription = "Star Icon",
            tint = MaterialTheme.colorScheme.primary
        )

        //  title
        Text(
            text = "Top Wizards",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
        )

        LazyRow(
            content = {
                items(allWizards) { character ->
                    CharacterItem(
                        character = character,
                        onHouseClicked = { onHouseClicked(character.house) },
                        onCharacterClicked = {

                            onWizardClicked(character)

//                            val myModelString = Json.encodeToString(
//                                CharacterModel.serializer(),
//                                character
//                            )

                            //  open detail screen
//                            direction.navigateToRoute(
//                                Screens.Detail.route,
//                                null
//                            )
                        }
                    )
                }
            },
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        )

    }
}


















