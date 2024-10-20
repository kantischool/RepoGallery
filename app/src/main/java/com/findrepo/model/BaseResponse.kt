package com.findrepo.model

import com.squareup.moshi.Json

data class BaseResponse<T>(
    @Json(name = "message") val message: String? = null,
    @Json(name = "data") val data: T? = null,
)

data class HeaderResponse(
    @Json(name = "authorization") val authorization: String? = null,
    @Json(name = "id") val id: String? = null,
)

data class ErrorResponse(
    @Json(name = "message") val message: String? = null,
)
