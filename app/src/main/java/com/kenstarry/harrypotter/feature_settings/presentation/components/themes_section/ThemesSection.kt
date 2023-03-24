package com.kenstarry.harrypotter.feature_settings.presentation.components.themes_section

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kenstarry.harrypotter.feature_settings.presentation.components.SectionTitle
import com.kenstarry.harrypotter.feature_settings.presentation.model.SettingsEvents
import com.kenstarry.harrypotter.feature_settings.presentation.util.SettingsConstants
import com.kenstarry.harrypotter.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun ThemesSection(
    modifier: Modifier = Modifier,
    settingsVM: SettingsViewModel
) {

    val context = LocalContext.current
    val savedTheme =
        settingsVM.themeFlow.collectAsState(initial = SettingsConstants.themeOptions[2].title)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            SectionTitle(
                title = "Themes",
                icon = Icons.Outlined.DarkMode,
                iconColor = MaterialTheme.colorScheme.primary,
                iconBackground = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            //  content
            LazyColumn(
                content = {
                    items(
                        SettingsConstants.themeOptions
                    ) {

                        ThemeRadioButton(
                            description = it.title,
                            icon = it.icon,
                            isSelected = it.title == savedTheme.value,
                            onRadioButtonClicked = {
                                //  toggle theme
                                settingsVM.onEvent(SettingsEvents.SetTheme(it.title))

                                Toast.makeText(context, "${it.title} activated", Toast.LENGTH_SHORT)
                                    .show()
                            },
                            primaryColor = MaterialTheme.colorScheme.primary,
                            tertiaryColor = MaterialTheme.colorScheme.tertiary
                        )
                    }
                },
                state = rememberLazyListState(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .height((50.dp + 16.dp) * SettingsConstants.themeOptions.size)
            )

        }

    }

}

























