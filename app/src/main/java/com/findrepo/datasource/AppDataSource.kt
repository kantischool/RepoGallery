package com.findrepo.datasource

import com.findrepo.api.ResponseState
import com.findrepo.model.BaseResponse
import retrofit2.Response

interface AppDataSource {
    suspend fun fetchRepositories(hashMap: HashMap<String, Any>): Response<Any>
}