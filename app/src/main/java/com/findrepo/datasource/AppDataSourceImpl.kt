package com.findrepo.datasource

import com.findrepo.api.ApiService
import com.findrepo.model.response.ContributorResponse
import com.findrepo.model.response.RepositoryListResponse
import retrofit2.Response
import javax.inject.Inject

class AppDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : AppDataSource {
    override suspend fun repositories(hashMap: HashMap<String, Any>) =
        apiService.repositories(hashMap)

    override suspend fun contributors(owner: String, repo: String) = apiService.contributors(owner, repo)
}