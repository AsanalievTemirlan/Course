package com.example.presentation.uitils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val bg_black = Color(0xFF151515)
val green = Color(0xFF12B956)
val blue = Color(0xFF2683ED)
val orangeGradient = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFFF98509),
        Color(0xFFF95D00)
    )
)
val light_gray = Color(0xFF32333A)
val white = Color(0xFFF2F2F3)
val dark_gray = Color(0xFF24252A)

fun Modifier.glassBackground(
    shape: Shape = RoundedCornerShape(12.dp)
): Modifier = this
    .clip(shape)
    .background(Color.Black.copy(alpha = 0.15f))
    .border(
        width = 1.dp,
        color = Color.White.copy(alpha = 0.35f),
        shape = shape
    )