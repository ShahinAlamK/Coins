package com.iconic.coins.data.services

import com.iconic.coins.data.dto.CoinDetailDto
import com.iconic.coins.data.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinServices {

    @GET("/v1/coins")
    suspend fun getAllCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}