package com.kenstarry.harrypotter.core.domain.use_case

import com.kenstarry.harrypotter.core.data.HarryPotterApi

class GetAllStaff(
    private val harryPotterApi: HarryPotterApi
) {
    suspend operator fun invoke() = harryPotterApi.getAllStaff()
}