package com.cuyer.cryptosearch.presentation.CryptoListings

sealed class CryptoListingsEvent {
    object Refresh: CryptoListingsEvent()
    data class OnSearchQueryChange(val query: String): CryptoListingsEvent()
}
