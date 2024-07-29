package com.iconic.coins.presentation.coins

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iconic.coins.domain.model.Coin

@Composable
fun CoinScreen(
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CoinViewModel = hiltViewModel()
) {
    val state = viewModel.uiState

    Scaffold { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.loading) {
                CircularProgressIndicator()
            } else if (state.error != null) {
                Text(text = state.error)
            } else {
                CoinList(
                    onClick = navigateToDetail,
                    coinList = state.coinList
                )
            }
        }
    }

}

@Composable
fun CoinList(
    modifier: Modifier = Modifier,
    coinList: List<Coin>,
    onClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(coinList.size) {
            CoinListItem(
                onClick = onClick,
                coin = coinList[it]
            )
        }
    }
}

@Composable
fun CoinListItem(modifier: Modifier = Modifier, onClick: (String) -> Unit, coin: Coin) {
    val color = if (coin.isActive == true) Color.Green else Color.Red
    val text = if (coin.isActive == true) "Active" else "Inactive"
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(coin.id!!) }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = coin.name.toString())
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "(${coin.symbol})")
        }

        Text(
            text = text,
            color = color
        )

    }
}