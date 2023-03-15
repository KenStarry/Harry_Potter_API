package com.example.harrypotter.core.domain.use_case

import com.example.harrypotter.core.domain.repository.HarryPotterApi

class GetCharacters(
    private val harryPotterApi: HarryPotterApi
) {
    suspend operator fun invoke() = harryPotterApi.getCharacters()
}