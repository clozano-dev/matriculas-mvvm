package com.clozanodev.matriculas.ui.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.clozanodev.matriculas.R
import com.clozanodev.matriculas.viewmodel.MainViewModel

@Composable
fun StatisticsScreen(viewModel: MainViewModel){
    val userStats by viewModel.userStats.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refreshUserStats()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.statistics_title), style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))

        userStats?.let { stats ->
            Text(text = stringResource(R.string.total_days_played, stats.totalDaysPlayed))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.gold_days, stats.totalGold))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.silver_days, stats.totalSilver))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.bronze_days, stats.totalBronze))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.highest_score, stats.maxScore))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.current_consecutive_gold, stats.currentConsecutiveGold))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.max_consecutive_gold, stats.maxConsecutiveGold))
        } ?: run {
            Text(text = "Loading statistics...")
        }
    }
}