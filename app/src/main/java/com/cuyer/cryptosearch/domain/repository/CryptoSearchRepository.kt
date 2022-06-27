package com.cuyer.cryptosearch.domain.repository

import com.cuyer.cryptosearch.domain.model.CryptoSearchModel
import com.cuyer.cryptosearch.util.Resource
import kotlinx.coroutines.flow.Flow

interface CryptoSearchRepository {

    suspend fun getCrypto(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CryptoSearchModel>>>
}