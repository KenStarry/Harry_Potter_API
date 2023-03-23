package com.kenstarry.harrypotter.feature_home.presentation.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    onQueryInput: (query: String) -> Unit
) {

    var query by remember {
        mutableStateOf("")
    }

    TextField(
        value = query,
        onValueChange = { newQuery ->
            query = newQuery
            onQueryInput(query)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search icon",
                tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
            )
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f),
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.secondary
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
            .fillMaxWidth(0.8f)
            .wrapContentHeight()
    )
}






























