package com.findrepo.api

import com.findrepo.api.ApiEndPoint.ContriButers
import com.findrepo.api.ApiEndPoint.REPOSITORIES
import com.findrepo.model.response.ContributorResponse
import com.findrepo.model.response.RepositoryListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {
    @GET(REPOSITORIES)
    suspend fun repositories(
        @QueryMap hashMap: HashMap<String, Any>
    ): Response<RepositoryListResponse>

    @GET(ContriButers)
    suspend fun contributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Response<List<ContributorResponse>>
}