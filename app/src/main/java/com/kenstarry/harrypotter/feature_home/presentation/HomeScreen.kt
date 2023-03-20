package com.kenstarry.harrypotter.feature_home.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
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
import com.kenstarry.harrypotter.core.presentation.components.WizardsShimmer
import com.kenstarry.harrypotter.core.presentation.util.Constants
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_home.domain.model.ResponseObserver
import com.kenstarry.harrypotter.feature_home.presentation.components.ErrorMessage
import com.kenstarry.harrypotter.feature_home.presentation.components.HogwartsStaffSection
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

    val isErrorVisible = remember {
        mutableStateOf(false)
    }

    val responseObserver = remember {

        ResponseObserver() { response ->

            if (response.isSuccessful) {

                //  show main UI
                isErrorVisible.value = false

                response.body()?.let {
                    allCharacters.value = it
                }

            } else {
                val responseMsg = response.message()

                isErrorVisible.value = true
            }
            Log.d("interceptor", response.message())
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

    //  check for error message

    AnimatedVisibility(visible = isErrorVisible.value) {
        ErrorMessage(message = "Connection timed out")
    }

    AnimatedVisibility(visible = !isErrorVisible.value) {
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
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {

                        //  wizards section
                        if (allCharacters.value.isEmpty()) {
                            //  show shimmer effects
                            WizardsShimmer()
                        } else {
                            //  wizards section
                            WizardsSection(
                                allWizards = allCharacters.value.filter { it.wizard },
                                direction = direction,
                                modifier = Modifier
                                    .wrapContentHeight()
                            )

                            //  hogwarts staff section
                            HogwartsStaffSection(
                                allHogwartsStaff = allCharacters.value.filter { it.hogwartsStaff },
                                direction = direction
                            )
                        }
                    }
                }
            }
        }
    }
}



















