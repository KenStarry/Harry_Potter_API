package com.kenstarry.harrypotter.feature_home.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kenstarry.harrypotter.core.domain.model.CharacterModel

@Composable
fun DetailBottomSheet(
    character: CharacterModel
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {

        Box(
            modifier = Modifier
                .size(200.dp)
        ) {

        }

    }

    Toast.makeText(context, character.name, Toast.LENGTH_SHORT).show()

}