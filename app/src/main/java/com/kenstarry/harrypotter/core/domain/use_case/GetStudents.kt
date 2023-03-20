package com.kenstarry.harrypotter.core.domain.use_case

import com.kenstarry.harrypotter.core.data.HarryPotterApi

class GetStudents(
    private val harryPotterApi: HarryPotterApi
) {
    suspend operator fun invoke() = harryPotterApi.getStudents()
}