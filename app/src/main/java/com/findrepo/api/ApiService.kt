package com.findrepo.api

import com.findrepo.api.ApiEndPoint.REPOSITORIES
import com.findrepo.model.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET(REPOSITORIES)
   suspend fun fetchRepositories(
        @QueryMap hashMap: HashMap<String, Any>
    ) : Response<Any>
}