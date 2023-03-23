package com.kenstarry.harrypotter.core.domain.model

sealed class CoreEvents {

    object GetCharacters : CoreEvents()
    object GetStudents : CoreEvents()
    object GetAllStaff : CoreEvents()
    object GetSpells : CoreEvents()
    data class GetCharacter(val id: String) : CoreEvents()

    data class GetCharactersInHouse(val houseName: String) : CoreEvents()

    data class SearchForCharacters(
        val query: String,
        val allCharacters: List<CharacterModel>,
        val filteredCharacters: (characters: List<CharacterModel>) -> Unit
    ) : CoreEvents()
}
