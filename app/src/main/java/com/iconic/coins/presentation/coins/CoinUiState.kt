package com.iconic.coins.presentation.coins

import com.iconic.coins.domain.model.Coin

data class CoinUiState(
    val coinList: List<Coin> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)