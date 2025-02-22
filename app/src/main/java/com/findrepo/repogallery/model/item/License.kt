package com.findrepo.repogallery.model.item

import com.squareup.moshi.Json

data class License(
    @Json(name = "key") val key: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "spdx_id") val spdxId: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "node_id") val nodeId: String? = null,
)
