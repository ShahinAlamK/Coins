package com.iconic.coins.domain.repository

import com.iconic.coins.data.dto.CoinDetailDto
import com.iconic.coins.data.dto.CoinDto


interface CoinRepository {
    suspend fun getAllCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}