package com.iconic.coins.mapper

import com.iconic.coins.data.dto.CoinDto
import com.iconic.coins.domain.model.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol,
        type = type,
        isNew = isNew
    )
}