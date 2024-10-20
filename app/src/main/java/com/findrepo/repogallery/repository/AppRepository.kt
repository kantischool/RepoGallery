package com.findrepo.repogallery.repository

import com.findrepo.api.ApiRequest
import com.findrepo.api.ResponseState
import com.findrepo.api.Status
import com.findrepo.datasource.AppDataSource
import com.findrepo.model.RepositoryListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val appDataSource: AppDataSource,
    private val api: ApiRequest,
) {
    fun fetchRepositories(hashMap: HashMap<String, Any>): Flow<ResponseState<RepositoryListResponse>> =
        flow {
            emit(ResponseState.Loading(Status.LOADING))
            val data = api.makeRequest {
                appDataSource.fetchRepositories(hashMap)
            }
            emit(data)
        }
}