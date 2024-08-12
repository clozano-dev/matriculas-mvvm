package com.clozanodev.matriculas.ui.rules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clozanodev.matriculas.R
import com.clozanodev.matriculas.ui.theme.Black
import com.clozanodev.matriculas.ui.theme.MainGreen
import com.clozanodev.matriculas.ui.theme.MatriculasTheme

val regularFont = FontFamily(Font(R.font.regular))

val titleTextStyle = TextStyle(
    fontSize = 32.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = regularFont,
    shadow = Shadow(color = Color.Gray, offset = Offset(1f, 1f), blurRadius = 2f),
    color = MainGreen,
    textAlign = TextAlign.Center
)

val subtitleTextStyle = TextStyle(
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = regularFont,
    color = MainGreen,
    textAlign = TextAlign.Start
)

val bodyTextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = regularFont,
    color = Black,
    textAlign = TextAlign.Start
)

@Composable
fun RulesScreen() {
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
        R.string.game_rules_valid_word_description_2
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
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.game_rules_title),
            style = titleTextStyle
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