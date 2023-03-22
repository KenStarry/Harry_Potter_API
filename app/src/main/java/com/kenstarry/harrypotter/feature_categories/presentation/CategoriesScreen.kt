package com.kenstarry.harrypotter.feature_categories.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kenstarry.harrypotter.feature_categories.presentation.components.CategoryTopBar
import com.kenstarry.harrypotter.feature_categories.presentation.util.CategoryConstants
import com.kenstarry.harrypotter.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    mainNavHostController: NavHostController,
    categoryContent: String
) {

    val direction = Direction(mainNavHostController)
    val icon = when (categoryContent) {
        CategoryConstants.CATEGORY_STAFF -> Icons.Outlined.School
        CategoryConstants.CATEGORY_STUDENT -> Icons.Outlined.Person
        else -> Icons.Outlined.Person
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

            }

        }

    }

}