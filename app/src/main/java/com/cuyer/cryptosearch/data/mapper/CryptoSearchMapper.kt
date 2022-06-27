package com.cuyer.cryptosearch.data.mapper

import com.cuyer.cryptosearch.data.local.CryptoSearchEntity
import com.cuyer.cryptosearch.domain.model.CryptoSearchModel

// Tworzymy logikę do mapowania danych z CryptoSearchEntity do CryptoSearchModel

fun CryptoSearchEntity.toCryptoSearchModel(): CryptoSearchModel {
    return CryptoSearchModel(
        name = name,
        symbol = symbol,
        is_new = is_new,
        is_active = is_active
    )
}

// Tworzymy logikę do mapowania danych z CryptoSearchModel do CryptoSearchEntity

fun CryptoSearchModel.toCryptoSearchEntity(): CryptoSearchEntity {
    return CryptoSearchEntity(
        name = name,
        symbol = symbol,
        is_new = is_new,
        is_active = is_active
    )
}