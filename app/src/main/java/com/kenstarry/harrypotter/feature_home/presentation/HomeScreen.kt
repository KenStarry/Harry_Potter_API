package com.kenstarry.harrypotter.feature_home.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.harrypotter.core.domain.model.CoreEvents
import com.kenstarry.harrypotter.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.harrypotter.feature_home.domain.model.ResponseObserver

@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val lifeCyclerOwner = LocalLifecycleOwner.current
    val responseObserver = remember {

        ResponseObserver(){ response ->
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
}