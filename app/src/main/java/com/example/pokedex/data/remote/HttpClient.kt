package com.example.pokedex.data.remote

import com.example.pokedex.data.remote.model.ErrorResponse
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class HttpClient @Inject constructor() {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T?
    ): RequestResult<T?> {
        return withContext(dispatcher) {
            try {
                setResult(apiCall.invoke())

            } catch (trowable: Throwable) {
                when (trowable) {
                    is TimeoutCancellationException -> {
                        RequestResult.TimeoutError
                    }

                    is IOException -> {
                        RequestResult.NetworkError
                    }

                    is HttpException -> {
                        mapHttpException(trowable)
                    }

                    else -> {
                        RequestResult.UndefinedError
                    }
                }
            }

        }
    }


    private suspend fun <T> mapHttpException(
        throwable: HttpException
    ): RequestResult<T?> {

        val responseBody = throwable.response()?.errorBody()
        val responseBodyStr = responseBody?.string()

        return if (responseBodyStr?.contains("undefined") == true) {

            val errorResponse = ErrorResponse(
                status = 404,
                message = "undefined"
            )
            RequestResult.RequestError(errorResponse)
        } else {
            RequestResult.UndefinedError
        }
    }


    private fun <T> setResult(apiCallResult: T?): RequestResult<T?> {
        return if (apiCallResult is Response<*>) {
            if (apiCallResult.isSuccessful) {
                RequestResult.Success(apiCallResult.body() as T?)
            } else {
                apiCallResult.errorBody()?.let {
                    RequestResult.RequestError(ErrorResponse())
                } ?: RequestResult.UndefinedError
            }
        } else {
            RequestResult.Success(apiCallResult)
        }

    }
}
