package com.clozanodev.matriculas.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.clozanodev.matriculas.R
import com.clozanodev.matriculas.ui.theme.Bronze
import com.clozanodev.matriculas.ui.theme.Gold
import com.clozanodev.matriculas.ui.theme.Silver


@Composable
fun ScoreCard(score: String, medal: String) {
    val (image, color) = when (medal) {
        "Gold" -> R.drawable.medal_gold to Gold
        "Silver" -> R.drawable.medal_silver to Silver
        "Bronze" -> R.drawable.medal_bronze to Bronze
        else -> R.drawable.sad to MaterialTheme.colorScheme.primary
    }

    val text = when (medal) {
        "Gold" -> "Medalla de oro"
        "Silver" -> "Medalla de plata"
        "Bronze" -> "Medalla de bronce"
        else -> "No hay medalla"
    }

    Card(

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp)
            .padding(horizontal = 64.dp)

    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Total: $score puntos",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier.size(96.dp)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

    }
}