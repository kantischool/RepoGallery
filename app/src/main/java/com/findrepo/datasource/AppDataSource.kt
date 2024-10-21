package com.findrepo.datasource

import com.findrepo.model.response.ContributorResponse
import com.findrepo.model.response.RepositoryListResponse
import retrofit2.Response

interface AppDataSource {
    suspend fun repositories(hashMap: HashMap<String, Any>): Response<RepositoryListResponse>

    suspend fun contributors(owner: String, repo: String): Response<List<ContributorResponse>>
}