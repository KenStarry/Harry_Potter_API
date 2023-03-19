package com.kenstarry.harrypotter.core.domain.model

sealed class MyResponse<out T> {

    data class Success<out T>(
        val data: T
    ) : MyResponse<T>()

    data class Failure<out T>(
        val error: T
    ) : MyResponse<T>()
}
