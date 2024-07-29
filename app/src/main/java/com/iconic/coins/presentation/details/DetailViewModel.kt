package com.iconic.coins.presentation.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iconic.coins.common.Resource
import com.iconic.coins.domain.user_case.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val id: String = checkNotNull(savedStateHandle["id"])

    var uiState by mutableStateOf(DetailUiState())
        private set

    init {
        getDetail(savedStateHandle.get<String>("id").toString())
    }

    private fun getDetail(id: String) {
        useCase(id).onEach {
            uiState = when (it) {
                is Resource.Error -> {
                    DetailUiState(error = it.message!!.localizedMessage)
                }

                is Resource.Loading -> {
                    DetailUiState(loading = true)
                }

                is Resource.Success -> {
                    DetailUiState(coinDetail = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}