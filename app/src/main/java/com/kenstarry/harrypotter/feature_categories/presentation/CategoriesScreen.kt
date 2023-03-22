package com.kenstarry.harrypotter.feature_categories.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.harrypotter.core.domain.model.BottomSheetEvents
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.domain.model.CoreEvents
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_categories.presentation.components.CategoryTopBar
import com.kenstarry.harrypotter.feature_categories.presentation.util.CategoryConstants
import com.kenstarry.harrypotter.feature_home.domain.model.ResponseObserver
import com.kenstarry.harrypotter.feature_home.presentation.components.CharacterItemAlt
import com.kenstarry.harrypotter.feature_home.presentation.util.HomeConstants
import com.kenstarry.harrypotter.navigation.Direction
import kotlinx.coroutines.CoroutineScope

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun CategoriesScreen(
    mainNavHostController: NavHostController,
    categoryContent: String,
    coreVM: CoreViewModel,
    state: ModalBottomSheetState,
    scope: CoroutineScope
) {

//    val coreVM: CoreViewModel = hiltViewModel()
    val lifeCyclerOwner = LocalLifecycleOwner.current
    val direction = Direction(mainNavHostController)
    val gridState = rememberLazyStaggeredGridState()

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

    val icon = when (categoryContent) {
        CategoryConstants.CATEGORY_STAFF -> Icons.Outlined.School
        CategoryConstants.CATEGORY_STUDENT -> Icons.Outlined.Person
        else -> Icons.Outlined.Person
    }

    if (categoryContent == CategoryConstants.CATEGORY_STAFF)
        coreVM.onEvent(CoreEvents.GetAllStaff)
    else if (categoryContent == CategoryConstants.CATEGORY_STUDENT)
        coreVM.onEvent(CoreEvents.GetStudents)

    DisposableEffect(lifeCyclerOwner, coreVM) {
        when (categoryContent) {

            CategoryConstants.CATEGORY_STAFF -> {
                coreVM.hogwartsStaff.observe(lifeCyclerOwner, responseObserver)

                onDispose {
                    coreVM.hogwartsStaff.removeObserver(responseObserver)
                }
            }

            CategoryConstants.CATEGORY_STUDENT -> {
                coreVM.hogwartsStudents.observe(lifeCyclerOwner, responseObserver)

                onDispose {
                    coreVM.hogwartsStudents.removeObserver(responseObserver)
                }
            }

            else -> {
                coreVM.hogwartsStaff.observe(lifeCyclerOwner, responseObserver)

                onDispose {
                    coreVM.hogwartsStaff.removeObserver(responseObserver)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CategoryTopBar(
                title = categoryContent,
                icon = icon,
                onBackPressed = {
                    direction.navigateUp()
                }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                //  list of characters
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    content = {
                        items(allCharacters.value) {
                            CharacterItemAlt(
                                character = it,
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
                    state = gridState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                )

            }

        }

    }

}