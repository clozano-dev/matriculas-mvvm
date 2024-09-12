package com.clozanodev.matriculas.ui.rules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun RulesScreen() {
    val subtitleTextStyle = MaterialTheme.typography.titleMedium.copy(
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold
    )

    val bodyTextStyle = MaterialTheme.typography.bodyLarge.copy(
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Start
    )

    val titleTextStyle = TextStyle(
        fontFamily = PlateFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        shadow = Shadow(
            color = Color.Gray.copy(alpha = 0.5f),
            offset = Offset(2f, 2f),
            blurRadius = 8f
        ),
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
    )

    val rules = listOf(
        R.string.game_rules_description,
        R.string.game_rules_score_title,
        R.string.game_rules_score_description,
        R.string.game_rules_score_letter_title,
        R.string.game_rules_score_letter_description,
        R.string.game_rules_multipliers_title,
        R.string.game_rules_multipliers_description_1,
        R.string.game_rules_multipliers_description_2,
        R.string.game_rules_multipliers_description_3,
        R.string.game_rules_total_score_title,
        R.string.game_rules_total_score_description_1,
        R.string.game_rules_total_score_description_2,
        R.string.game_rules_total_score_description_3,
        R.string.game_rules_total_score_description_4,
        R.string.game_rules_valid_words_title,
        R.string.game_rules_valid_word_description_1,
        R.string.game_rules_valid_word_description_2,
        R.string.game_rules_valid_word_description_3
    )

    val combinedItems = buildList {
        rules.forEachIndexed { index, ruleId ->
            add(ruleId)
            if (index == 4) {
                add(null)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título principal
        Text(
            text = stringResource(R.string.game_rules_title),
            style = titleTextStyle,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
        )

        // Reglas dentro de una LazyColumn
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            items(combinedItems) { item ->
                if (item == null) {
                    // Composable personalizado para puntuaciones
                    MyLetterScores()
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    val text = stringResource(id = item)
                    val style = when (item) {
                        R.string.game_rules_score_title -> subtitleTextStyle
                        R.string.game_rules_score_letter_title -> subtitleTextStyle
                        R.string.game_rules_multipliers_title -> subtitleTextStyle
                        R.string.game_rules_total_score_title -> subtitleTextStyle
                        R.string.game_rules_valid_words_title -> subtitleTextStyle
                        else -> bodyTextStyle
                    }

                    // Añadimos un ícono al lado de los títulos importantes
                    if (style == subtitleTextStyle) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(end = 8.dp)
                            )
                            Text(
                                text = text,
                                style = style
                            )
                        }
                        Divider(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    } else {
                        Text(
                            text = text,
                            style = style,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
