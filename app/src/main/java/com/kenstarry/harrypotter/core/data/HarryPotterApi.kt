package com.kenstarry.harrypotter.core.data

import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.domain.model.Spell
import com.kenstarry.harrypotter.core.presentation.util.Constants.Companion.CHARACTERS_ENDPOINT
import com.kenstarry.harrypotter.core.presentation.util.Constants.Companion.HOUSES_ENDPOINT
import com.kenstarry.harrypotter.core.presentation.util.Constants.Companion.SPELLS_ENDPOINT
import com.kenstarry.harrypotter.core.presentation.util.Constants.Companion.STAFF_ENDPOINT
import com.kenstarry.harrypotter.core.presentation.util.Constants.Companion.STUDENTS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HarryPotterApi {

    //  get all characters
    @GET(CHARACTERS_ENDPOINT)
    suspend fun getCharacters(): Response<List<CharacterModel>>

    //  characters who are Hogwarts students
    @GET(STUDENTS_ENDPOINT)
    suspend fun getStudents(): Response<List<CharacterModel>>

    //  characters who are Hogwarts staff
    @GET(STAFF_ENDPOINT)
    suspend fun getAllStaff(): Response<List<CharacterModel>>

    //  get all spells
    @GET(SPELLS_ENDPOINT)
    suspend fun getSpells(): Response<List<Spell>>

    //  get characters in a house
    @GET(HOUSES_ENDPOINT)
    suspend fun getCharactersInHouse(
        @Path("houseName") houseName:String
    ): Response<List<CharacterModel>>

    //  get character with specific id
    @GET(CHARACTERS_ENDPOINT)
    suspend fun getCharacter(
        @Query("id") characterId: String
    ) : Response<CharacterModel>

}















