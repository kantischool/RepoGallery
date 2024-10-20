package com.findrepo.state

data class RepositoryScreenState (
    val isLoading: Boolean = false
)

sealed class RepositoryScreenUiEvent{
    data object RepoClick : RepositoryScreenUiEvent()
}