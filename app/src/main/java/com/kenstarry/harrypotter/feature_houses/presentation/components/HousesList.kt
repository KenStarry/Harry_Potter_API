package com.kenstarry.harrypotter.feature_houses.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.harrypotter.core.domain.model.BottomSheetEvents
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.domain.model.CoreEvents
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_home.domain.model.ResponseObserver
import com.kenstarry.harrypotter.feature_home.presentation.util.HomeConstants
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HousesList(
    allHouses: List<String>,
    coreVM: CoreViewModel,
    state: ModalBottomSheetState,
    scope: CoroutineScope
) {

    val listState = rememberLazyListState()
    val lifeCyclerOwner = LocalLifecycleOwner.current

    val allCharacters = remember {
        mutableStateOf<List<CharacterModel>>(emptyList())
    }

    val responseObserver = remember {
        ResponseObserver() { response ->
            response.body()?.let {
                allCharacters.value = it
            }
        }
    }

    DisposableEffect(lifeCyclerOwner, coreVM) {
        coreVM.harryPotterCharacters.observe(lifeCyclerOwner, responseObserver)

        onDispose {
            coreVM.harryPotterCharacters.removeObserver(responseObserver)
        }
    }

    //  observe characters in the specific house
    coreVM.onEvent(CoreEvents.GetCharacters)

    //  populate a list of houses
    LazyColumn(
        content = {
            items(allHouses) { house ->

                val charactersInHouse = allCharacters.value.filter { it.house == house }

                //  lazy row to display all characters in that house
                HousesListItem(
                    houseName = house,
                    charactersInHouse = charactersInHouse,
                    onCharacterClicked = {
                        coreVM.onBottomSheetEvent(
                            BottomSheetEvents.OpenBottomSheet(
                                state = state,
                                scope = scope,
                                bottomSheetType = HomeConstants.DETAILS_BOTTOM_SHEET,
                                bottomSheetData = it
                            )
                        )
                    },
                    onHouseClicked = {}
                )

            }
        },
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )


}