package com.iconic.coins.data.repository

import com.iconic.coins.data.dto.CoinDetailDto
import com.iconic.coins.data.dto.CoinDto
import com.iconic.coins.data.services.CoinServices
import com.iconic.coins.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val coinServices: CoinServices) :
    CoinRepository {

    override suspend fun getAllCoins(): List<CoinDto> = coinServices.getAllCoins()

    override suspend fun getCoinById(coinId: String): CoinDetailDto =
        coinServices.getCoinById(coinId)


}