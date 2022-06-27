package com.cuyer.cryptosearch.presentation.CryptoListings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuyer.cryptosearch.domain.repository.CryptoSearchRepository
import com.cuyer.cryptosearch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListingsViewModel @Inject constructor(
    private val repository: CryptoSearchRepository
): ViewModel() {

    var state by mutableStateOf(CryptoListingsState())
    private var searchJob: Job? = null

    init {
        getCrypto("")
    }

    fun onEvent(event: CryptoListingsEvent) {
        when(event) {
            is CryptoListingsEvent.Refresh -> {
                getCrypto(fetchFromRemote = true)
            }
            is CryptoListingsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCrypto()
                }
            }
        }
    }

    private fun getCrypto(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getCrypto(fetchFromRemote, query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { crypto ->
                                state = state.copy(
                                    crypto = crypto
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

}