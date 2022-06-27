package com.cuyer.cryptosearch.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptoSearchDao {

    // Definiujemy funkcje do interakcji z bazą danych: Insert, Clear, Query

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrypto(
        cryptoSearchEntities: List<CryptoSearchEntity>
    )

    @Query("DELETE FROM cryptosearchentity")
    suspend fun clearCrypto()

    @Query (
            """
            SELECT *
            FROM cryptosearchentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == symbol
            """
            )
    suspend fun searchCrypto(query: String): List<CryptoSearchEntity>

}