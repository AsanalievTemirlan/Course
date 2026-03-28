package com.example.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.presentation.uitils.white

@Composable
fun BaseText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = white,
    fontSize: TextUnit = 16.sp,
    weight: FontWeight = FontWeight.W500
) {

    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        modifier = modifier,
        fontWeight = weight
    )

}