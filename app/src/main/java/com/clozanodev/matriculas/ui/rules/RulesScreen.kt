package com.clozanodev.matriculas.ui.rules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clozanodev.matriculas.R

val regularFont = FontFamily(Font(R.font.roboto_regular))



@Composable
fun RulesScreen() {
    val subtitleTextStyle = MaterialTheme.typography.titleMedium.copy(
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
    )

    val bodyTextStyle = MaterialTheme.typography.bodyLarge.copy(
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Start
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
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
/*
        MyPlate(text = stringResource(R.string.game_rules_title))
*/
        Text(
            text = stringResource(R.string.game_rules_title),
            style = MaterialTheme.typography.titleLarge.copy(
                shadow = Shadow(color = Color.Gray, offset = Offset(1f, 1f), blurRadius = 2f),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start
        ) {
            items(combinedItems) { item ->
                if (item == null) {
                    MyLetterScores()
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

                    val paddingModifier = if (style == subtitleTextStyle) {
                        Modifier.padding(top = 16.dp, bottom = 2.dp)
                    } else {
                        Modifier.padding(4.dp)
                    }
                    Text(
                        text = text, style = style, modifier = paddingModifier
                    )
                }
            }

        }

    }
}
