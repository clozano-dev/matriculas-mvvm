package com.clozanodev.matriculas.ui.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.clozanodev.matriculas.R
import com.clozanodev.matriculas.viewmodel.MainViewModel

@Composable
fun StatisticsScreen(viewModel: MainViewModel) {
    val userStats by viewModel.userStats.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refreshUserStats()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.statistics_title),
            style = MaterialTheme.typography.titleLarge.copy(
                shadow = Shadow(color = Color.Gray, offset = Offset(1f, 1f), blurRadius = 2f),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        )

        userStats?.let { stats ->
            StatisticsCard(
                title = stringResource(R.string.total_days_played),
                value = stats.totalDaysPlayed.toString(),
                iconResId = R.drawable.days
            )

            StatisticsCard(
                title = stringResource(R.string.gold_days),
                value = stats.totalGold.toString(),
                iconResId = R.drawable.trophy_gold
            )

            StatisticsCard(
                title = stringResource(R.string.silver_days),
                value = stats.totalSilver.toString(),
                iconResId = R.drawable.trophy_silver
            )

            StatisticsCard(
                title = stringResource(R.string.bronze_days),
                value = stats.totalBronze.toString(),
                iconResId = R.drawable.trophy_bronze
            )

            StatisticsCard(
                title = stringResource(R.string.highest_score),
                value = stats.maxScore.toString(),
                iconResId = R.drawable.high_score
            )

            StatisticsCard(
                title = stringResource(R.string.current_consecutive_gold),
                value = stats.currentConsecutiveGold.toString(),
                iconResId = R.drawable.current_streak
            )

            StatisticsCard(
                title = stringResource(R.string.max_consecutive_gold),
                value = stats.maxConsecutiveGold.toString(),
                iconResId = R.drawable.max_streak
            )
        }
    }
}


