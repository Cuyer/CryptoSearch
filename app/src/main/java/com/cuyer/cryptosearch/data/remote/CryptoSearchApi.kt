package com.cuyer.cryptosearch.data.remote

import com.cuyer.cryptosearch.data.remote.dto.CryptoSearchDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path


interface CryptoSearchApi {

    // pobieranie listy kryptowalut
    @GET("/v1/coins/")
    suspend fun getCoins(): List<CryptoSearchDto>

    // pobieranie informacji na temat konkretnej kryptowaluty
    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): ResponseBody

    companion object {
        const val BASE_URL = "https://api.coinpaprika.com/"
    }
}