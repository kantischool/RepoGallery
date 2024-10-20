package com.findrepo.datasource

import com.findrepo.api.ApiService
import javax.inject.Inject

class AppDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : AppDataSource {
    override suspend fun fetchRepositories(hashMap: HashMap<String, Any>) =
        apiService.fetchRepositories(hashMap)
}