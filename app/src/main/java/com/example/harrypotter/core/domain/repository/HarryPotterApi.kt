package com.example.harrypotter.core.domain.repository

import com.example.harrypotter.core.domain.model.CharacterModel
import com.example.harrypotter.core.presentation.util.Constants.Companion.CHARACTERS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface HarryPotterApi {

    @GET(CHARACTERS_ENDPOINT)
    suspend fun getCharacters(): Response<List<CharacterModel>>

}