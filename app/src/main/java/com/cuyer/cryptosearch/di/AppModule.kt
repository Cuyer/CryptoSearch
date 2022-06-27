package com.cuyer.cryptosearch.di

import android.app.Application
import androidx.room.Room
import com.cuyer.cryptosearch.data.local.CryptoSearchDatabase
import com.cuyer.cryptosearch.data.remote.CryptoSearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptoApi(): CryptoSearchApi {
        return Retrofit.Builder()
            .baseUrl(CryptoSearchApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideCryptoDatabase(app: Application): CryptoSearchDatabase {
        return Room.databaseBuilder(
            app,
            CryptoSearchDatabase::class.java,
            "Cryptodb.db"
        ).build()
    }

}