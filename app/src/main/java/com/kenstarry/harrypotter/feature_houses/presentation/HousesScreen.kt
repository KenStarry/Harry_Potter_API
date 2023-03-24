package com.kenstarry.harrypotter.feature_houses.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.domain.model.CoreEvents
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_home.domain.model.ResponseObserver
import com.kenstarry.harrypotter.feature_houses.presentation.components.HousesList
import com.kenstarry.harrypotter.feature_houses.presentation.components.HousesTopBar
import com.kenstarry.harrypotter.navigation.Direction
import com.kenstarry.harrypotter.navigation.screens.BottomNavScreens
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HousesScreen(
    navHostController: NavHostController,
    coreVM: CoreViewModel,
    state: ModalBottomSheetState,
    scope: CoroutineScope
) {

//    val coreVM: CoreViewModel = hiltViewModel()
    val directionInner = Direction(navHostController)
    val lifeCyclerOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

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

    //  get all characters
    coreVM.onEvent(CoreEvents.GetCharacters)

    DisposableEffect(lifeCyclerOwner, coreVM) {
        coreVM.harryPotterCharacters.observe(lifeCyclerOwner, responseObserver)

        onDispose {
            coreVM.harryPotterCharacters.removeObserver(responseObserver)
        }
    }

    val allHouses = allCharacters.value.map { it.house }.distinct()

    Scaffold(
        topBar = {
            HousesTopBar(
                title = "Houses",
                onBackPressed = {
                    //  navigate back to home screen
                    directionInner.navigateToRoute(
                        BottomNavScreens.Home.route,
                        BottomNavScreens.Home.route
                    )
                }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
            ) {

                //  display a list of all houses
                HousesList(
                    allHouses = allHouses,
                    coreVM = coreVM,
                    state = state,
                    scope = scope
                )

            }

        }
    }
}





























