package com.nesyou.daily.features.home.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesyou.daily.R

@Composable
fun ColumnScope.StaggeredItem(
    weight: Float,
    colors: List<Color>,
    @DrawableRes icon: Int,
    mainColor: Color = Color.White,
    onClick: () -> Unit = {},
    title: String,
    length: Int,
) {
    Box(
        modifier = Modifier
            .weight(weight)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(brush = Brush.verticalGradient(colors))
            .clickable(
                onClick = onClick
            )
            .padding(horizontal = 18.dp, vertical = 20.dp),
        content = {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "arrow",
                    modifier = Modifier.size(11.dp),
                    tint = mainColor
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Image(painter = painterResource(id = icon), contentDescription = null)
                Text(title, color = mainColor, style = MaterialTheme.typography.h3)
                Text(
                    "$length Task",
                    color = mainColor,
                    fontSize = 16.sp
                )
            }
        }
    )
}