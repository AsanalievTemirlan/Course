package com.example.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.CourseModel
import com.example.presentation.R
import com.example.presentation.uitils.dark_gray
import com.example.presentation.uitils.glassBackground
import com.example.presentation.uitils.green
import com.example.presentation.uitils.white

@Composable
fun CourseItem(course: CourseModel, favorite: (CourseModel) -> Unit) {
    val imageRes = when (course.id % 3) {
        0 -> R.drawable.ic_im1
        1 -> R.drawable.ic_im2
        else -> R.drawable.ic_im3
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(dark_gray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.3f))
                    .clickable {
                        favorite(course)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = if (course.hasLike) R.drawable.ic_favorites_fill else R.drawable.ic_favorite_empty),
                    contentDescription = null,
                    tint = if (course.hasLike) green else white,
                    modifier = Modifier.size(18.dp)
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .glassBackground(RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = green,
                        modifier = Modifier.size(14.dp)
                    )
                    Spa(4)
                    Text(
                        text = course.rate,
                        color = white,
                        fontSize = 12.sp
                    )
                }

                Spa(8)

                Box(
                    modifier = Modifier
                        .glassBackground(RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = course.startDate,
                        color = white,
                        fontSize = 12.sp
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Spa(12)

            Text(
                text = course.title,
                color = white,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spa(8)

            Text(
                text = course.text,
                color = Color.Gray,
                fontSize = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spa(12)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${course.price} ₽",
                    color = white,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier.clickable { /* Navigate to detail */ },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Подробнее",
                        color = green,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spa(4)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_green_arrow),
                        contentDescription = null,
                        tint = green,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}