package com.clozanodev.matriculas.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clozanodev.matriculas.R
import com.clozanodev.matriculas.ui.theme.MainFontFamily
import com.clozanodev.matriculas.ui.theme.PlateFontFamily


val PlateTextStyle = TextStyle(
    fontFamily = PlateFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 48.sp
)

@Composable
fun MyPlate(text: String) {
    Box(
        modifier = Modifier.padding(16.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.plate),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = text,
            style = PlateTextStyle,
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp).padding(start = 16.dp)
                .align(Alignment.Center)
        )
    }
}