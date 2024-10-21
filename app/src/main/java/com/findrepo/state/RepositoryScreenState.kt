package com.findrepo.state

import com.findrepo.model.Repository

data class RepositoryScreenState (
    val isLoading: Boolean = false,
    val repositories: List<Repository> = emptyList(),
    val query: String = "",
    val isNetWorkNotAvailable: Boolean = false,
    val isCallApiAgain: Boolean = false,
    val isDataLoading: Boolean = false,
)

sealed class RepositoryScreenUiEvent{
    data object RepoClick : RepositoryScreenUiEvent()

    data object SearchRepo : RepositoryScreenUiEvent()

    data object CallApiAgain : RepositoryScreenUiEvent()

    data object NextApiCall : RepositoryScreenUiEvent()

    data object DataFromRoomDB : RepositoryScreenUiEvent()

    data object NetWorkAvailable : RepositoryScreenUiEvent()

    data class ChangeQuery(val query: String) : RepositoryScreenUiEvent()
}