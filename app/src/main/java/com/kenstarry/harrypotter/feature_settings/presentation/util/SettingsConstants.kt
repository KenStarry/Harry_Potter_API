package com.kenstarry.harrypotter.feature_settings.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Light
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Nightlight
import com.kenstarry.harrypotter.feature_settings.presentation.model.SettingsSectionItem

object SettingsConstants {

    //  theme section
    val themeOptions = listOf(
        SettingsSectionItem("Dark Theme", Icons.Outlined.Nightlight),
        SettingsSectionItem("Light Theme", Icons.Outlined.LightMode),
        SettingsSectionItem("Follow System", Icons.Outlined.Light)
    )
}