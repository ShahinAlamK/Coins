package com.iconic.coins.di

import com.iconic.coins.data.repository.CoinRepositoryImpl
import com.iconic.coins.data.services.CoinServices
import com.iconic.coins.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideCoinServices(retrofit: Retrofit): CoinServices {
        return retrofit.create(CoinServices::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(coinServices: CoinServices): CoinRepository {
        return CoinRepositoryImpl(coinServices)
    }


}