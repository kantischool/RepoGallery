package com.findrepo.repogallery.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findrepo.api.ResponseState
import com.findrepo.repogallery.repository.AppRepository
import com.findrepo.state.RepositoryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class RepositoryViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val appRepository: AppRepository,
): ViewModel()  {
    private val TAG = "RepositoryViewModel"
    private val uiState = MutableStateFlow(RepositoryScreenState())
    fun consumableState() = uiState.asStateFlow()

    init {
        fetchRepository("kotlin")
    }

    private fun fetchRepository(query: String) {
        val hashMap: HashMap<String, Any> = hashMapOf(
            "q" to query,
            "page" to 1,
            "per_page" to 10
        )
        appRepository.fetchRepositories(hashMap).onEach { response ->
            when(response) {
                is ResponseState.Loading -> {
                    uiState.value = uiState.value.copy(
                        isLoading = true
                    )
                }
                is ResponseState.Success -> {
                    Log.e(TAG, "fetchRepository: success", )
                    uiState.value = uiState.value.copy(
                        isLoading = false
                    )
                }
                is ResponseState.Error -> {
                    Log.e(TAG, "fetchRepository: fail", )
                    uiState.value = uiState.value.copy(
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}