package com.findrepo.repogallery.datasource

import com.findrepo.repogallery.api.ApiService
import javax.inject.Inject

class AppDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : AppDataSource {
    override suspend fun repositories(hashMap: HashMap<String, Any>) =
        apiService.repositories(hashMap)

    override suspend fun contributors(owner: String, repo: String) =
        apiService.contributors(owner, repo)
}