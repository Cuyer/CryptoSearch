package com.cuyer.cryptosearch.di

import com.cuyer.cryptosearch.data.local.repository.CryptoSearchImpl
import com.cuyer.cryptosearch.domain.repository.CryptoSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCryptoRepository(
        cryptoSearchImpl: CryptoSearchImpl
    ): CryptoSearchRepository
}