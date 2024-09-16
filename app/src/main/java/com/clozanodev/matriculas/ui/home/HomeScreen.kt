package com.clozanodev.matriculas.ui.home

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clozanodev.matriculas.R
import com.clozanodev.matriculas.game.containsAllLetters
import com.clozanodev.matriculas.game.containsAllLettersInOrder
import com.clozanodev.matriculas.game.containsNumbers
import com.clozanodev.matriculas.viewmodel.MainViewModel
import com.clozanodev.matriculas.ui.theme.Green
import com.clozanodev.matriculas.ui.theme.PlateFontFamily
import com.clozanodev.matriculas.ui.theme.Red

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val context = LocalContext.current

    val licensePlate by viewModel.licensePlate.collectAsState()
    val realTimeScore by viewModel.realTimeScore.collectAsState()
    val totalScore by viewModel.totalScore.collectAsState()
    val medal by viewModel.medal.collectAsState()
    val isGameLocked by viewModel.isGameLocked.collectAsState()
    val submittedWordsAndScores = viewModel.submittedWordsAndScores

    var word by remember { mutableStateOf("") }
    var wordsSubmitted by remember { mutableStateOf(0) }

    var containsAllLetters by remember { mutableStateOf(false) }
    var containsAllLettersInOrder by remember { mutableStateOf(false) }
    var containsNumbers by remember { mutableStateOf(false) }

    var isFocused by remember { mutableStateOf(false) }
    val focusRequest = remember { FocusRequester() }

    val titleTextStyle = TextStyle(
        fontFamily = PlateFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        shadow = Shadow(
            color = Color.Gray, offset = Offset(1f, 1f), blurRadius = 2f
        ),
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
    )

    LaunchedEffect(Unit) {
        viewModel.fetchCurrentLicensePlate()
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
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = stringResource(R.string.plates_title),
            style = titleTextStyle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )

        MyPlate(text = licensePlate?.plate.orEmpty())

        if (!isGameLocked) {
            OutlinedTextField(value = word, onValueChange = { input ->
                if (input.all { it.isLetter() }) {
                    word = input.uppercase()
                }
            }, label = {
                if (!isFocused) {
                    Text(stringResource(R.string.insert_word))
                }
            }, maxLines = 1, keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Text
            ), textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                fontFamily = PlateFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ), visualTransformation = { text ->
                TransformedText(
                    text = text.toUpperCase(), offsetMapping = OffsetMapping.Identity
                )

            },


                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.primary,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                    focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                    unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .padding(horizontal = 8.dp)
                    .focusRequester(focusRequest)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            var rulesCardHeight by remember { mutableStateOf(0) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .onGloballyPositioned { coordinates ->
                            rulesCardHeight = coordinates.size.height
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {

                        val rule1Color by animateColorAsState(
                            targetValue = if (containsAllLetters) Green else Red,
                            animationSpec = tween(durationMillis = 500)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = if (containsAllLetters) Icons.Default.Check else Icons.Default.Close,
                                contentDescription = null,
                                tint = rule1Color,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Todas las letras",
                                color = rule1Color,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        val rule2Color by animateColorAsState(
                            targetValue = if (containsAllLettersInOrder) Green else Red,
                            animationSpec = tween(durationMillis = 500)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = if (containsAllLettersInOrder) Icons.Default.Check else Icons.Default.Close,
                                contentDescription = null,
                                tint = rule2Color,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Letras en orden",
                                color = rule2Color,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        val rule3Color by animateColorAsState(
                            targetValue = if (containsNumbers) Green else Red,
                            animationSpec = tween(durationMillis = 500)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = if (containsNumbers) Icons.Default.Check else Icons.Default.Close,
                                contentDescription = null,
                                tint = rule3Color,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Número de letras",
                                color = rule3Color,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(start = 8.dp)
                        .height(with(LocalDensity.current) { rulesCardHeight.toDp() }),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = realTimeScore.toString(),
                            style = TextStyle(
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = PlateFontFamily,
                                color = MaterialTheme.colorScheme.primary,
                                shadow = Shadow(
                                    color = Color.Black.copy(alpha = 0.2f),
                                    offset = Offset(4f, 4f),
                                    blurRadius = 8f
                                )
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    when {
                        !viewModel.verifyWord(word) -> {
                            Toast.makeText(
                                context,
                                "La palabra no existe en el diccionario",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        !viewModel.checkPreviousWord(word) -> {
                            Toast.makeText(
                                context,
                                "La palabra ya ha sido enviada",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else -> {
                            viewModel.submitWord(word)
                            word = ""
                            wordsSubmitted += 1
                        }
                    }
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ), shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.submit_word),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }


        } else {
            Text(
                text = stringResource(R.string.return_tomorrow),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            submittedWordsAndScores.forEach { (word, score) ->

                WordsCard(word = word, score = score.toString())
            }
        }


        if (isGameLocked) {
            ScoreCard(score = totalScore.toString(), medal = medal)
        }
    }
}