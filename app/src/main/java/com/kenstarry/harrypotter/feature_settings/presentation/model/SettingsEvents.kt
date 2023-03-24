package com.kenstarry.harrypotter.feature_settings.presentation.model

sealed class SettingsEvents {

    data class SetTheme(val theme: String) : SettingsEvents()

//    data class ToggleThemeRadioBtn(
//        val selectedTheme: String
//    ) : SettingsEvents()
}
