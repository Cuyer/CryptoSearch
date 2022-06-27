package com.cuyer.cryptosearch.data.local.repository

import android.util.Log
import com.cuyer.cryptosearch.data.local.CryptoSearchDatabase
import com.cuyer.cryptosearch.data.mapper.toCryptoSearchEntity
import com.cuyer.cryptosearch.data.mapper.toCryptoSearchModel
import com.cuyer.cryptosearch.data.remote.CryptoSearchApi
import com.cuyer.cryptosearch.data.remote.dto.CryptoSearchDto
import com.cuyer.cryptosearch.data.remote.dto.toCryptoSearch
import com.cuyer.cryptosearch.domain.model.CryptoSearchModel
import com.cuyer.cryptosearch.domain.repository.CryptoSearchRepository
import com.cuyer.cryptosearch.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoSearchImpl @Inject constructor(
    private val api: CryptoSearchApi,
    private val db: CryptoSearchDatabase
): CryptoSearchRepository {

    private val dao = db.dao

    override suspend fun getCrypto(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CryptoSearchModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCrypto = dao.searchCrypto(query)
            emit(Resource.Success(
                data = localCrypto.map { it.toCryptoSearchModel() }
            ))

            // Sprawdzamy czy potrzebujemy połączenia z API

            val isDBEmpty = localCrypto.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDBEmpty && !fetchFromRemote
            if (shouldLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            // API call

            val remoteCryptoCall = try {
                val response = api.getCoins()
                response.map { it.toCryptoSearch() }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteCryptoCall?.let { crypto ->
                dao.clearCrypto()
                dao.insertCrypto(
                    crypto.map { it.toCryptoSearchEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchCrypto("")
                        .map { it.toCryptoSearchModel() }
                ))
                //emit(Resource.Loading(false))
            }

        }
    }

}