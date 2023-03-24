package com.kenstarry.harrypotter.feature_settings.di

import android.content.Context
import com.kenstarry.harrypotter.feature_settings.data.datastore.ThemePreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    //  provide theme preference
    @Provides
    @Singleton
    fun provideThemePreference(
        @ApplicationContext context: Context
    ) = ThemePreference(context)
}