package com.iconic.coins.presentation.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iconic.coins.data.dto.Team
import com.iconic.coins.domain.model.CoinDetails

@Composable
fun CoinDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.uiState

    Scaffold { paddingValues ->
        if (state.loading) {
            LoadingComponent(modifier = modifier.padding(paddingValues))
        } else if (state.error != null) {
            Text(text = state.error)
        } else if (state.coinDetail != null) {
            Column(
                modifier = modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                Details(details = state.coinDetail)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Details(modifier: Modifier = Modifier, details: CoinDetails) {
    val color = if (details.isActive == true) Color.Green else Color.Red
    val text = if (details.isActive == true) "Active" else "Inactive"
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${details.rank}. ${details.name} (${details.symbol})",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(8f)
                )
            }
            Text(text = text, color = color)
        }

        if (!details.description.isNullOrEmpty()) {
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Description", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(18.dp))
            Text(text = details.description)
        }

        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Tags", fontWeight = FontWeight.Bold)
        FlowRow(
            maxItemsInEachRow = 4,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            details.tags?.forEach { tag ->
                CoinTag(tag = tag?.name!!)
            }
        }

        if (details.tags!!.isNotEmpty()) {
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Teams", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(8.dp))
            details.team!!.forEach { team ->
                TeamListItem(
                    teamMember = team!!,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                Divider()
            }
        }

    }
}

@Composable
fun CoinTag(
    tag: String
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = tag,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun TeamListItem(
    teamMember: Team,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name!!,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position!!,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}