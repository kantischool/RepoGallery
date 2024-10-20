package com.findrepo.datasource

import com.findrepo.model.RepositoryListResponse
import retrofit2.Response

interface AppDataSource {
    suspend fun fetchRepositories(hashMap: HashMap<String, Any>): Response<RepositoryListResponse>
}