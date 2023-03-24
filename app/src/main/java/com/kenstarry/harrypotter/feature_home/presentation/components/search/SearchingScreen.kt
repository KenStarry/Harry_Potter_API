package com.kenstarry.harrypotter.feature_home.presentation.components.search

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.kenstarry.harrypotter.R
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.domain.model.CoreEvents
import com.kenstarry.harrypotter.core.presentation.components.Lottie
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_home.domain.model.ResponseObserver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchingScreen(
    allCharacters: List<CharacterModel>,
    onClearClicked: () -> Unit,
    onCharacterClicked: (character: CharacterModel) -> Unit
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val context = LocalContext.current
    coreVM.onEvent(CoreEvents.GetCharacters)

    val filteredCharacters = remember {
        mutableStateOf<List<CharacterModel>>(emptyList())
    }

    Toast.makeText(context, allCharacters.size.toString(), Toast.LENGTH_SHORT).show()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            SearchTopBar(
                onQueryInput = { query ->
                    //  filter the characters
                    coreVM.onEvent(CoreEvents.SearchForCharacters(
                        query = query,
                        allCharacters = allCharacters,
                        filteredCharacters = {
                            filteredCharacters.value = it
                        }
                    ))
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                onClearClicked()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "Search icon",
                    tint = MaterialTheme.colorScheme.error
                )
            }

        }

        //  no characters found
        AnimatedVisibility(visible = filteredCharacters.value.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Lottie(
                    rawFile = R.raw.search_orange,
                    isPlaying = true,
                    iterations = Int.MAX_VALUE,
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "No characters found",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )

            }
        }

        //  some characters found
        AnimatedVisibility(visible = filteredCharacters.value.isNotEmpty()) {

            val listState = rememberLazyListState()

            LazyColumn(
                content = {
                    items(filteredCharacters.value) { character ->
                        SearchCharacterItem(
                            character = character,
                            onCharacterClicked = { onCharacterClicked(character) },
                            onHouseClicked = {}
                        )
                    }
                },
                state = listState,
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
        }


    }
}
















