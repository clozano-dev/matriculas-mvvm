package com.clozanodev.matriculas.ui.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clozanodev.matriculas.R
import com.clozanodev.matriculas.ui.theme.PlateFontFamily
import com.clozanodev.matriculas.viewmodel.MainViewModel

@Composable
fun StatisticsScreen(viewModel: MainViewModel) {
    val userStats by viewModel.userStats.collectAsState()

    val titleTextStyle = TextStyle(
        fontFamily = PlateFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        shadow = Shadow(
            color = Color.Gray,
            offset = Offset(1f, 1f),
            blurRadius = 2f
        ), color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center
    )

    LaunchedEffect(Unit) {
        viewModel.refreshUserStats()
    }

    val statisticsList = listOf(
        Triple(
            stringResource(R.string.total_days_played),
            userStats?.totalDaysPlayed.toString(),
            R.drawable.days
        ), Triple(
            stringResource(R.string.gold_days),
            userStats?.totalGold.toString(),
            R.drawable.medal_gold
        ), Triple(
            stringResource(R.string.silver_days),
            userStats?.totalSilver.toString(),
            R.drawable.medal_silver
        ), Triple(
            stringResource(R.string.bronze_days),
            userStats?.totalBronze.toString(),
            R.drawable.medal_bronze
        ), Triple(
            stringResource(R.string.highest_score),
            userStats?.maxScore.toString(),
            R.drawable.high_score
        ), Triple(
            stringResource(R.string.current_consecutive_gold),
            userStats?.currentConsecutiveGold.toString(),
            R.drawable.current_streak
        ), Triple(
            stringResource(R.string.max_consecutive_gold),
            userStats?.maxConsecutiveGold.toString(),
            R.drawable.max_streak
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.statistics_title),
            style = titleTextStyle,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {


            items(statisticsList) { (title, value, iconResId) ->
                StatisticsCard(
                    title = title, value = value, iconResId = iconResId
                )
            }
        }
    }
}


