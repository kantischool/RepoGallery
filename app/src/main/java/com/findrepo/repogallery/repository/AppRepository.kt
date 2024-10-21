package com.findrepo.repogallery.repository

import com.findrepo.repogallery.api.ApiRequest
import com.findrepo.repogallery.api.ResponseState
import com.findrepo.repogallery.api.Status
import com.findrepo.repogallery.datasource.AppDataSource
import com.findrepo.repogallery.model.response.ContributorResponse
import com.findrepo.repogallery.model.response.RepositoryListResponse
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
                appDataSource.repositories(hashMap)
            }
            emit(data)
        }

    fun contributors(owner: String, repo: String): Flow<ResponseState<List<ContributorResponse>>> =
        flow {
            emit(ResponseState.Loading(Status.LOADING))
            val data = api.makeRequest {
                appDataSource.contributors(owner, repo)
            }
            emit(data)
        }
}