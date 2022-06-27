package com.cuyer.cryptosearch.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CryptoSearchEntity (
    @PrimaryKey val id: Int? = null,
    val name: String,
    val symbol: String,
    val is_new: String,
    val is_active: String,
        )