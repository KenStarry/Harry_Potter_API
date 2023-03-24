package com.kenstarry.harrypotter.feature_home.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.kenstarry.harrypotter.core.domain.model.BottomSheetEvents
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.domain.model.CoreEvents
import com.kenstarry.harrypotter.core.presentation.components.WizardsShimmer
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_categories.presentation.util.CategoryConstants
import com.kenstarry.harrypotter.feature_home.domain.model.ResponseObserver
import com.kenstarry.harrypotter.feature_home.presentation.components.*
import com.kenstarry.harrypotter.feature_home.presentation.components.search.SearchingScreen
import com.kenstarry.harrypotter.feature_home.presentation.util.HomeConstants
import com.kenstarry.harrypotter.navigation.Direction
import com.kenstarry.harrypotter.navigation.screens.Screens
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainNavHostController: NavHostController,
    navHostController: NavHostController,
    coreVM: CoreViewModel,
    state: ModalBottomSheetState,
    scope: CoroutineScope
) {

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

    var isSearching by remember {
        mutableStateOf(false)
    }

    //  check for error message

//    AnimatedVisibility(visible = isErrorVisible.value) {
//        ErrorMessage(message = "Connection timed out")
//    }

    AnimatedVisibility(visible = isSearching) {
        SearchingScreen(
            allCharacters = allCharacters.value,
            onClearClicked = { isSearching = false },
            onCharacterClicked = {
                coreVM.onBottomSheetEvent(
                    BottomSheetEvents.OpenBottomSheet(
                        state = state,
                        scope = scope,
                        bottomSheetType = HomeConstants.DETAILS_BOTTOM_SHEET,
                        bottomSheetData = it
                    )
                )
            }
        )
    }

    AnimatedVisibility(visible = !isSearching) {
        //  show main content
        IntroShowCaseScaffold(
            showIntroShowCase = showAppIntro,
            onShowCaseCompleted = { showAppIntro = false }
        ) {

            Scaffold(
                topBar = {
                    HomeTopBar(
                        onSearch = {
                            //   change screen to implement search
                            isSearching = true
                        },
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
                            .verticalScroll(rememberScrollState())
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
                                    .wrapContentHeight(),
                                onWizardClicked = {
                                    coreVM.onBottomSheetEvent(
                                        BottomSheetEvents.OpenBottomSheet(
                                            state = state,
                                            scope = scope,
                                            bottomSheetType = HomeConstants.DETAILS_BOTTOM_SHEET,
                                            bottomSheetData = it
                                        )
                                    )
                                },
                                onHouseClicked = {

                                }
                            )

                            //  hogwarts staff section
                            HogwartsStaffSection(
                                allHogwartsStaff = allCharacters.value.filter { it.hogwartsStaff },
                                direction = direction,
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
                                onHouseClicked = {},
                                onSeeAll = {
                                    //  open category screen
                                    direction.navigateToRoute(
                                        Screens.Category.passCategory(CategoryConstants.CATEGORY_STAFF),
                                        null
                                    )
                                }
                            )

                            //  hogwarts staff section
                            HogwartsStudentsSection(
                                allHogwartsStudents = allCharacters.value.filter { it.hogwartsStudent },
                                direction = direction,
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
                                onHouseClicked = {},
                                onSeeAll = {
                                    //  open category screen
                                    direction.navigateToRoute(
                                        Screens.Category.passCategory(CategoryConstants.CATEGORY_STUDENT),
                                        null
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}



















