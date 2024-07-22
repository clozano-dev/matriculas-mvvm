package com.clozanodev.matriculas.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.clozanodev.matriculas.R
import com.clozanodev.matriculas.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val licensePlate by viewModel.licensePlate.collectAsState()
    val realTimeScore by viewModel.realTimeScore.collectAsState()
    val totalScore by viewModel.totalScore.collectAsState()
    val medal by viewModel.medal.collectAsState()

    var word by remember { mutableStateOf("") }
    var wordsSubmitted by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.getLicensePlate(1)
    }

    LaunchedEffect(word) {
        viewModel.calculateRealTimeScore(word)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("License Plate: ${licensePlate?.plate}")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = word,
            onValueChange = { input ->
                if (input.all {it.isLetter()}){
                    word = input.uppercase()
                }
            },
            label = { Text(stringResource(R.string.insert_word)) },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Text
            ),
            visualTransformation = { text ->
                TransformedText(
                    text = text.toUpperCase(),
                    offsetMapping = OffsetMapping.Identity
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.score, realTimeScore))

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.submitWord(word)
                word = ""
                wordsSubmitted +=1
            }
        ){
            Text(stringResource(R.string.submit_word))
        }

        Text(text = stringResource(R.string.total_score, totalScore))
        Text(text = stringResource(R.string.medal, medal))

        if (wordsSubmitted == 3){
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                viewModel.resetGame()
                wordsSubmitted = 0
            }) {
                Text("Restart game")
            }
        }
    }
}