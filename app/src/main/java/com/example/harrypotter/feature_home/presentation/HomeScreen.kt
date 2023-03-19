package com.example.harrypotter.feature_home.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.harrypotter.core.domain.model.CoreEvents
import com.example.harrypotter.core.presentation.viewmodel.CoreViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val lifeCyclerOwner = LocalLifecycleOwner.current

    coreVM.onEvent(CoreEvents.GetCharacters)

    coreVM.harryPotterCharacters.observe(lifeCyclerOwner) { response ->

        response.body()?.get(0)?.let {
            Log.d("response", it.name)
            Log.d("response", it.house)
            Log.d("response", it.actor)
            Log.d("response", it.eyeColour)
        }

//        response.body()?.forEachIndexed { index, character ->
//            Log.d("response", character.id)
//            Log.d("response", character.name)
//            Log.d("response", character.house)
//        }

    }
}