package com.iconic.coins.presentation.details

import com.iconic.coins.domain.model.CoinDetails

data class DetailUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val coinDetail: CoinDetails? = null
)
