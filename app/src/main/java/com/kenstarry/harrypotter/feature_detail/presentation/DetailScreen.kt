package com.kenstarry.harrypotter.feature_detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.kenstarry.harrypotter.feature_detail.presentation.components.DetailTopBar
import com.kenstarry.harrypotter.feature_detail.domain.model.CharacterObserver
import com.kenstarry.harrypotter.navigation.Direction

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    characterId: String
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val direction = Direction(navHostController)
    val lifeCycleOwner = LocalLifecycleOwner.current

    var showAppIntro by remember {
        mutableStateOf(false)
    }

    coreVM.onEvent(CoreEvents.GetCharacter(characterId))

    val characterDetails = remember {
        mutableStateOf<CharacterModel?>(null)
    }

    val characterObserver = remember {
        CharacterObserver() {response ->
            if (response.isSuccessful) {
                response.body().let {
                    characterDetails.value = it
                }
            }
        }
    }

    DisposableEffect(lifeCycleOwner, coreVM) {
        coreVM.selectedCharacter.observe(lifeCycleOwner, characterObserver)

        onDispose {
            coreVM.selectedCharacter.removeObserver(characterObserver)
        }
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showAppIntro,
        onShowCaseCompleted = { showAppIntro = false }
    ) {

        Scaffold(
            topBar = {
                DetailTopBar(title = characterDetails.value?.name ?: "") {
                    direction.navigateUp()
                }
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

                }
            }
        }
    }


}