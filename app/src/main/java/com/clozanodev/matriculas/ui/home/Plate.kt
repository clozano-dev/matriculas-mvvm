package com.clozanodev.matriculas.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clozanodev.matriculas.R
import com.clozanodev.matriculas.ui.theme.PlateFontFamily


val PlateTextStyle = TextStyle(
    fontFamily = PlateFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 56.sp
)

@Composable
fun MyPlate(text: String) {
    val numbers = text.take(4)
    val letters = text.takeLast(3)

    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(280.dp, 80.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.plate),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        Row(
            modifier = Modifier
                .align(Alignment.Center).padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = numbers,
                style = PlateTextStyle,
                color = Color.Black
            )

            Text(
                text = letters,
                style = PlateTextStyle,
                color = Color.Black
            )
        }
    }
}