package com.findrepo.state

import com.findrepo.repogallery.model.item.Repository
import com.findrepo.repogallery.model.response.ContributorResponse

data class RepositoryDetailState(
    val isLoading: Boolean = false,
    val repo: Repository? = null,
    val contributors: List<ContributorResponse> = emptyList()
)

sealed class RepositoryDetailUiEvent {
    data class RepositoryData(val repo: Repository) : RepositoryDetailUiEvent()
}