package com.kenstarry.harrypotter.core.presentation.util

class Constants {

    companion object {

        const val BASE_URL = "https://hp-api.onrender.com/api/"

        //  endpoints
        const val CHARACTERS_ENDPOINT = "characters"
        const val STUDENTS_ENDPOINT = "characters/students"
        const val STAFF_ENDPOINT = "characters/staff"

        val alphabets = listOf(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
        )
    }
}