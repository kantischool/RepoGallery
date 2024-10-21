package com.findrepo.repogallery.model.response

import com.findrepo.repogallery.model.item.Repository
import com.squareup.moshi.Json

data class RepositoryListResponse(
    @Json(name = "total_count") val totalCount: Int? = null,
    @Json(name = "incomplete_results") val incompleteResults: Boolean? = null,
    @Json(name = "items") val items: List<Repository>? = null
)

