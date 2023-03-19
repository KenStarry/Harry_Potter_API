package com.kenstarry.harrypotter.feature_home.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.kenstarry.harrypotter.core.domain.model.CoreEvents
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_home.domain.model.ResponseObserver
import com.kenstarry.harrypotter.feature_home.presentation.components.HomeTopBar

@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val lifeCyclerOwner = LocalLifecycleOwner.current

    val responseObserver = remember {

        ResponseObserver() { response ->
            response.body()?.get(0)?.let {
                Log.d("response", it.name)
                Log.d("response", it.house)
                Log.d("response", it.actor)
                Log.d("response", it.eyeColour)
            }
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
                        .verticalScroll(rememberScrollState())
                ) {

                }
            }
        }
    }
}



















