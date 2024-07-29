package com.iconic.coins.presentation.coins

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iconic.coins.common.Resource
import com.iconic.coins.domain.user_case.GetAllCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(private val useCase: GetAllCoinUseCase) : ViewModel() {

    var uiState by mutableStateOf(CoinUiState())
        private set

    init {
        getAllCoins()
    }

    private fun getAllCoins() {
        useCase().onEach {
            uiState = when (it) {
                is Resource.Error -> {
                    CoinUiState(error = (it.message ?: "Unknown error").toString())
                }

                is Resource.Loading -> {
                    CoinUiState(loading = true)
                }

                is Resource.Success -> {
                    CoinUiState(coinList = it.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}