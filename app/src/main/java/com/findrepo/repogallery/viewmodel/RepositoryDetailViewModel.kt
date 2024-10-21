package com.findrepo.repogallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findrepo.repogallery.api.ResponseState
import com.findrepo.repogallery.repository.AppRepository
import com.findrepo.state.RepositoryDetailState
import com.findrepo.state.RepositoryDetailUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailViewModel @Inject constructor(
    private val apiRepository: AppRepository
) : ViewModel() {

    private val uiState = MutableStateFlow(RepositoryDetailState())
    fun consumableState() = uiState.asStateFlow()

    fun onEvent(event: RepositoryDetailUiEvent) {
        when (event) {
            is RepositoryDetailUiEvent.RepositoryData -> {
                uiState.update {
                    it.copy(
                        repo = event.repo
                    )
                }
                contributors()
            }
        }
    }

    private fun contributors() {
        val repo = uiState.value.repo
        apiRepository.contributors(
            owner = repo?.owner?.login.toString(),
            repo = repo?.name.toString()
        ).onEach { response ->
            when (response) {
                is ResponseState.Loading -> {
                    uiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is ResponseState.Success -> {

                    uiState.update {
                        it.copy(
                            isLoading = false,
                            contributors = response.data ?: emptyList()
                        )
                    }
                }
                is ResponseState.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}