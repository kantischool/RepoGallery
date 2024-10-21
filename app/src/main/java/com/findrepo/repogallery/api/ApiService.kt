package com.findrepo.repogallery.api

import com.findrepo.repogallery.api.ApiEndPoint.CONTRIBUTORS
import com.findrepo.repogallery.api.ApiEndPoint.REPOSITORIES
import com.findrepo.repogallery.model.response.ContributorResponse
import com.findrepo.repogallery.model.response.RepositoryListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {
    @GET(REPOSITORIES)
    suspend fun repositories(
        @QueryMap hashMap: HashMap<String, Any>
    ): Response<RepositoryListResponse>

    @GET(CONTRIBUTORS)
    suspend fun contributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Response<List<ContributorResponse>>
}