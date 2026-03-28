package com.example.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun SocialButton(
    icon: Painter,
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null,
    brush: Brush? = null,
    onClick: () -> Unit = {}
) {

    Box(
        modifier = modifier
            .height(40.dp)
            .width(156.dp)
            .clip(RoundedCornerShape(60))
            .then(
                when {
                    brush != null -> Modifier.background(brush)
                    backgroundColor != null -> Modifier.background(backgroundColor)
                    else -> Modifier.background(Color.Gray)
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(40.dp).padding(6.dp)
        )
    }
}