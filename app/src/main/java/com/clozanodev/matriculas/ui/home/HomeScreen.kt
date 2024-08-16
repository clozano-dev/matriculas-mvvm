package com.clozanodev.matriculas.ui.home

import android.widget.Toast
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.clozanodev.matriculas.R
import com.clozanodev.matriculas.game.containsAllLetters
import com.clozanodev.matriculas.game.containsAllLettersInOrder
import com.clozanodev.matriculas.game.containsNumbers
import com.clozanodev.matriculas.ui.MyPlate
import com.clozanodev.matriculas.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val context = LocalContext.current

    val licensePlate by viewModel.licensePlate.collectAsState()
    val realTimeScore by viewModel.realTimeScore.collectAsState()
    val totalScore by viewModel.totalScore.collectAsState()
    val medal by viewModel.medal.collectAsState()
    val isGameLocked by viewModel.isGameLocked.collectAsState()

    var word by remember { mutableStateOf("") }
    var wordsSubmitted by remember { mutableStateOf(0) }

    var containsAllLetters by remember { mutableStateOf(false) }
    var containsAllLettersInOrder by remember { mutableStateOf(false) }
    var containsNumbers by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchCurrentLicensePlate(false)
        viewModel.loadGameState()
    }

    LaunchedEffect(word) {
        if (!isGameLocked) {
            viewModel.calculateRealTimeScore(word)

            containsAllLetters = word.containsAllLetters(licensePlate?.plate)
            containsAllLettersInOrder = word.containsAllLettersInOrder(licensePlate?.plate)
            containsNumbers = word.containsNumbers(licensePlate?.plate)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        
        Text(
            text = stringResource(R.string.plates),
            style = MaterialTheme.typography.titleLarge.copy(
                shadow = Shadow(
                    color = androidx.compose.ui.graphics.Color.Gray,
                    offset = Offset(1f, 1f),
                    blurRadius = 2f
                ), color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth(),
        )

        MyPlate(text = "${licensePlate?.plate}")

        if (!isGameLocked){
            TextField(
                value = word,
                onValueChange = { input ->
                    if (input.all { it.isLetter() }) {
                        word = input.uppercase()
                    }
                },
                label = { Text(stringResource(R.string.insert_word)) },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
                ),
                visualTransformation = { text ->
                    TransformedText(
                        text = text.toUpperCase(), offsetMapping = OffsetMapping.Identity
                    )

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                enabled = !isGameLocked
            )

            Text(
                text = stringResource(R.string.rule_1),
                color = if (containsAllLetters) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = stringResource(R.string.rule_2),
                color = if (containsAllLettersInOrder) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = stringResource(R.string.rule_3),
                color = if (containsNumbers) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodyLarge,
            )


        } else {
            Text(
                text = stringResource(R.string.return_tomorrow),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.score, realTimeScore))

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (viewModel.verifyWord(word) && viewModel.checkPreviousWord(word)) {
                    viewModel.submitWord(word)
                    word = ""
                    wordsSubmitted += 1
                } else {
                    Toast.makeText(
                        context,
                        if (!viewModel.verifyWord(word)) "La palabra no existe en el diccionario" else "La palabra ya ha sido enviada",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }, enabled = !isGameLocked
        ) {
            Text(stringResource(R.string.submit_word))
        }

        Text(text = stringResource(R.string.total_score, totalScore))
        Text(text = stringResource(R.string.medal, medal))

    }
}