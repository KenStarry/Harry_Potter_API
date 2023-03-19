package com.kenstarry.harrypotter.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.domain.model.CoreEvents
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_home.domain.model.ResponseObserver
import com.kenstarry.harrypotter.feature_home.presentation.components.WizardsSection
import com.kenstarry.harrypotter.feature_home.presentation.components.HomeTopBar
import com.kenstarry.harrypotter.navigation.Direction
import java.util.*

@Composable
fun HomeScreen(
    mainNavHostController: NavHostController,
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val direction = Direction(mainNavHostController)
    val directionInner = Direction(navHostController)
    val lifeCyclerOwner = LocalLifecycleOwner.current

    val allCharacters = remember {
        mutableStateOf<List<CharacterModel>>(emptyList())
    }

    val responseObserver = remember {

        ResponseObserver() { response ->
            response.body()?.let {
                allCharacters.value = it
            }
//            response.body()?.get(0)?.let {
//                Log.d("response", it.name)
//                Log.d("response", it.house)
//                Log.d("response", it.actor)
//                Log.d("response", it.eyeColour)
//            }
        }

    }

    coreVM.onEvent(CoreEvents.GetCharacters)

    DisposableEffect(lifeCyclerOwner, coreVM) {
        coreVM.harryPotterCharacters.observe(lifeCyclerOwner, responseObserver)

        onDispose {
            coreVM.harryPotterCharacters.removeObserver(responseObserver)
        }
    }

    var showAppIntro by remember {
        mutableStateOf(false)
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showAppIntro,
        onShowCaseCompleted = { showAppIntro = false }
    ) {

        Scaffold(
            topBar = {
                HomeTopBar(
                    onSearch = {},
                    onMore = {}
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

                    WizardsSection(
                        allWizards = allCharacters.value.filter { it.wizard },
                        direction = direction,
                        modifier = Modifier
                            .wrapContentHeight()
                    )
                }
            }
        }
    }
}



















