package com.kenstarry.harrypotter.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kenstarry.harrypotter.R
import com.kenstarry.harrypotter.core.presentation.components.Lottie
import com.kenstarry.harrypotter.feature_home.presentation.components.search.SearchTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchingScreen(
    onClearClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        SearchTopBar(
            onClearClicked = onClearClicked
        )

        Lottie(
            rawFile = R.raw.search_orange,
            isPlaying = true,
            iterations = Int.MAX_VALUE,
            modifier = Modifier
                .fillMaxSize(0.7f)
        )

    }
}