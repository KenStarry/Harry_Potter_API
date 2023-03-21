package com.kenstarry.harrypotter.feature_home.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            .fillMaxWidth()
            .height(800.dp)
            .verticalScroll(rememberScrollState())
    ) {

    }

    Toast.makeText(context, character.name, Toast.LENGTH_SHORT).show()

}