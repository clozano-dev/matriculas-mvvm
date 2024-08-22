package com.clozanodev.matriculas.ui.rules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MyLetterScores() {
    val cellStyle = MaterialTheme.typography.bodyLarge.copy(
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Start
    )

    val tableData = listOf(
        listOf("A = 1", "B = 4", "C = 2", "D = 2", "E = 1"),
        listOf("F = 6", "G = 4", "H = 6", "I = 1", "J = 8"),
        listOf("K = 10", "L = 2", "M = 3", "N = 1", "Ñ = 10"),
        listOf("O = 1", "P = 3", "Q = 6", "R = 1", "S = 1"),
        listOf("T = 2", "U = 3", "V = 6", "W = 10", "X = 10"),
        listOf("Y = 6", "Z = 8")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        tableData.forEach { rowData ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowData.forEach { cellData ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cellData,
                            style = cellStyle
                        )
                    }
                }
            }
        }
    }
}