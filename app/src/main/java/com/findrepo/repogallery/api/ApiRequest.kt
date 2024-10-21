package com.findrepo.repogallery.api

import com.findrepo.repogallery.model.ErrorResponse
import com.findrepo.utility.util.parseStringToJSON
import javax.inject.Inject
import retrofit2.Response

class ApiRequest @Inject constructor() {
    suspend fun <T : Any> makeRequest(
        call: suspend () -> Response<T>
    ) = createResource(call)

    private suspend fun <T : Any> createResource(
        call: suspend () -> Response<T>,
    ): ResponseState<T> {
        val responseState: ResponseState<T> = try {
            val response = call.invoke()

            if (response.isSuccessful) {
                ResponseState.Success(
                    status = Status.SUCCESS,
                    responseCode = response.code(),
                    data = response.body(),
                )
            } else {
                val errorBody: String? = response.errorBody()?.string()
                val errorResponse = errorBody.parseStringToJSON(ErrorResponse::class.java)

                ResponseState.Error(
                    status = Status.ERROR,
                    responseCode = response.code(),
                    error = errorResponse,
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseState.Error(
                status = Status.ERROR,
                responseCode = null,
                error = ErrorResponse(e.message),
            )
        }

        return responseState
    }
}