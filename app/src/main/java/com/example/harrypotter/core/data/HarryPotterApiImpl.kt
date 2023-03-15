package com.example.harrypotter.core.data

import com.example.harrypotter.core.domain.model.CharacterModel
import com.example.harrypotter.core.domain.repository.HarryPotterApi
import retrofit2.Response
import javax.inject.Inject

class HarryPotterApiImpl @Inject constructor(
    private val harryPotterApi: HarryPotterApi
) {

    suspend fun getCharacters(): Response<List<CharacterModel>> {
        return harryPotterApi.getCharacters()
    }

    //    suspend fun getCharacters(): Response<List<CharacterModel>> {
//        return harryPotterApi.getCharacters()
//    }

}