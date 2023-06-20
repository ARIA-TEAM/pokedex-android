package com.example.pokedex.data.remote

import com.example.pokedex.data.remote.model.ErrorResponse

sealed class RequestResult<out T> {

    data class Success<out T>(val data: T) : RequestResult<T>()
    data class RequestError(
        val errorResponse: ErrorResponse = ErrorResponse()
    ) : RequestResult<Nothing>()

    object TimeoutError : RequestResult<Nothing>()
    object NetworkError : RequestResult<Nothing>()
    object UndefinedError : RequestResult<Nothing>()

}