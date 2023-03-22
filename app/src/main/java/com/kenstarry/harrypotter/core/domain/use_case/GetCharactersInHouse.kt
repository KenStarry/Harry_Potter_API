package com.kenstarry.harrypotter.core.domain.use_case

import com.kenstarry.harrypotter.core.data.HarryPotterApi

class GetCharactersInHouse(
    private val harryPotterApi: HarryPotterApi
) {
    suspend operator fun invoke(
        houseName: String
    ) = harryPotterApi.getCharactersInHouse(houseName)
}