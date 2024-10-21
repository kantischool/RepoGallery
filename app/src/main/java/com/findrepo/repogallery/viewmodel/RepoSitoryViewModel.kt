package com.findrepo.repogallery.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findrepo.api.ResponseState
import com.findrepo.model.Repository
import com.findrepo.repogallery.repository.AppRepository
import com.findrepo.roomdb.RepoDatabase
import com.findrepo.state.RepositoryScreenState
import com.findrepo.state.RepositoryScreenUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RepositoryViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val appRepository: AppRepository,
    private val repoDatabase: RepoDatabase
) : ViewModel() {

    private val TAG = "RepositoryViewModel"

    private val uiState = MutableStateFlow(RepositoryScreenState())
    fun consumableState() = uiState.asStateFlow()

    private var page: Int = 1
    private var apiCount: Int = 0
    private var isDataLoading: Boolean = false

    init {
        fetchRepository("q")
    }

    fun onEvent(event: RepositoryScreenUiEvent) {
        when (event) {
            is RepositoryScreenUiEvent.RepoClick -> {

            }

            is RepositoryScreenUiEvent.ChangeQuery -> {
                uiState.update {
                    it.copy(
                        query = event.query
                    )
                }
            }

            is RepositoryScreenUiEvent.SearchRepo -> {
                page = 1
                isDataLoading = false
                uiState.update {
                    it.copy(repositories = emptyList())
                }
                fetchRepository(uiState.value.query)
            }

            is RepositoryScreenUiEvent.CallApiAgain -> {
                if (apiCount == 0) {
                    apiCount++
                } else {
                    uiState.update { it.copy(isCallApiAgain = true) }
                }
            }

            is RepositoryScreenUiEvent.NextApiCall -> {
                page++
                isDataLoading = true
                fetchRepository(uiState.value.query)
            }

            is RepositoryScreenUiEvent.DataFromRoomDB -> {
                viewModelScope.launch {
                    val list = repoDatabase.repoDao().getRepositories()
                    uiState.update {
                        it.copy(
                            repositories = list,
                            isNetWorkNotAvailable = true
                        )
                    }
                }
            }

            is RepositoryScreenUiEvent.NetWorkAvailable -> {
                uiState.update {
                    it.copy(
                        isNetWorkNotAvailable = false
                    )
                }
            }
        }
    }

    private fun fetchRepository(query: String) {
        val queryStr = query.ifEmpty { "Q" }
        val hashMap: HashMap<String, Any> = hashMapOf(
            "q" to queryStr,
            "page" to page,
            "per_page" to 10
        )

        if (!uiState.value.isNetWorkNotAvailable) {
            appRepository.fetchRepositories(hashMap).onEach { response ->
                when (response) {
                    is ResponseState.Loading -> {
                        uiState.update {
                            it.copy(
                                isLoading = !isDataLoading,
                                isDataLoading = isDataLoading
                            )
                        }
                    }

                    is ResponseState.Success -> {
                        val list = if (uiState.value.repositories.isNotEmpty()) {
                            uiState.value.repositories + (response.data?.items ?: emptyList())
                        } else {
                            response.data?.items ?: emptyList()
                        }
                        uiState.update {
                            it.copy(
                                isLoading = false,
                                isCallApiAgain = false,
                                repositories = list
                            )
                        }
                        insertInRoomDb(response.data?.items ?: emptyList())
                    }

                    is ResponseState.Error -> {
                        uiState.update {
                            it.copy(
                                isCallApiAgain = false,
                                isLoading = false
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private suspend fun insertInRoomDb(items: List<Repository>) {
        val list = repoDatabase.repoDao().getRepositories()
        if (list.isEmpty() || 15 - list.size >= 10) {
            repoDatabase.repoDao().addRepositories(items)
        } else {
            val idx = 15 - list.size
            val data = list.slice(0..<idx)
            repoDatabase.repoDao().addRepositories(data)
        }
    }
}