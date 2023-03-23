package com.kenstarry.harrypotter.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            SearchTopBar()
            
            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                onClearClicked()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "Search icon",
                    tint = MaterialTheme.colorScheme.error
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Lottie(
                rawFile = R.raw.search_orange,
                isPlaying = true,
                iterations = Int.MAX_VALUE,
                modifier = Modifier
                    .fillMaxSize(0.7f)
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "No characters found",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary
            )

        }

    }
}