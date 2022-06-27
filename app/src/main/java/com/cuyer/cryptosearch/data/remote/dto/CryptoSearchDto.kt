package com.cuyer.cryptosearch.data.remote.dto

import com.cuyer.cryptosearch.domain.model.CryptoSearchModel

data class CryptoSearchDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CryptoSearchDto.toCryptoSearch(): CryptoSearchModel {
    return CryptoSearchModel(
        name = name,
        symbol = symbol,
        is_new = is_new.toString(),
        is_active = is_active.toString()
    )
}