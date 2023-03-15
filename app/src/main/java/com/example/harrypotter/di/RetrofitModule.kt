package com.example.harrypotter.di

import com.example.harrypotter.core.data.HarryPotterApiImpl
import com.example.harrypotter.core.domain.repository.HarryPotterApi
import com.example.harrypotter.core.domain.use_case.GetCharacters
import com.example.harrypotter.core.domain.use_case.HarryPotterUseCases
import com.example.harrypotter.core.presentation.util.Constants
import com.example.harrypotter.core.presentation.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideHarryPotterApi(retrofit: Retrofit): HarryPotterApi =
        retrofit.create(HarryPotterApi::class.java)

    @Provides
    @Singleton
    fun provideHarryPotterApiImpl(
        harryPotterApi: HarryPotterApi
    ) = HarryPotterApiImpl(harryPotterApi)

    @Provides
    @Singleton
    fun provideHarryPotterUseCases(
        harryPotterApi: HarryPotterApi
    ) = HarryPotterUseCases(
        getCharacters = GetCharacters(harryPotterApi)
    )
}















