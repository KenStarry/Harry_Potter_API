package com.kenstarry.harrypotter.di

import com.kenstarry.harrypotter.core.data.HarryPotterApi
import com.kenstarry.harrypotter.core.data.MyInterceptor
import com.kenstarry.harrypotter.core.domain.use_case.GetCharacters
import com.kenstarry.harrypotter.core.domain.use_case.HarryPotterUseCases
import com.kenstarry.harrypotter.core.presentation.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .addInterceptor(MyInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideHarryPotterApi(retrofit: Retrofit): HarryPotterApi =
        retrofit.create(HarryPotterApi::class.java)

    @Provides
    @Singleton
    fun provideHarryPotterUseCases(
        harryPotterApi: HarryPotterApi
    ) = HarryPotterUseCases(
        getCharacters = GetCharacters(harryPotterApi)
    )
}















