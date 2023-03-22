package com.kenstarry.harrypotter.core.domain.use_case

data class HarryPotterUseCases(
    val getCharacters: GetCharacters,
    val getStudents: GetStudents,
    val getAllStaff: GetAllStaff,
    val getSpells: GetSpells,
    val getCharacter: GetCharacter,
    val getCharactersInHouse: GetCharactersInHouse
)
