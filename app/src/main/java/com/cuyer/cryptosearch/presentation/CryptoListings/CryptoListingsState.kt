package com.cuyer.cryptosearch.presentation.CryptoListings

import com.cuyer.cryptosearch.domain.model.CryptoSearchModel

data class CryptoListingsState(
    val crypto: List<CryptoSearchModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)