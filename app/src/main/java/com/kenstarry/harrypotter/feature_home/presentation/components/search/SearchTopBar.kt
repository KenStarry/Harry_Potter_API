package com.kenstarry.harrypotter.feature_home.presentation.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    onClearClicked: () -> Unit
) {

    val query by remember {
        mutableStateOf("")
    }

    TextField(
        value = query,
        onValueChange = { onQueryChanged ->

        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search icon",
                tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
            )
        },
        trailingIcon = {

            if (query.isBlank()) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "Search icon",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .clickable { onClearClicked() }
                )
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)
        ),
        placeholder = {
            Text(text = "gryffindor, harry potter etc...")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(45.dp)
    )
}






























