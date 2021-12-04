package com.nesyou.daily.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nesyou.daily.R

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    backColor: Color = Color(0xFFF9FAFD),
    mainColor: Color = Color(0xFF8F99EB),
    onClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = backColor,
        modifier = Modifier
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.medium))
                    .padding(vertical = dimensionResource(id = R.dimen.medium)),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .clip(CircleShape)
                            .background(mainColor)
                    )
                    Column {
                        Text(
                            "Cleaning Clothes",
                            style = MaterialTheme.typography.h3,
                        )
                        Text(
                            "07:00 - 07:30",
                            color = MaterialTheme.colors.onSecondary,
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(start = 13.dp)
                ) {
                    Tag(label = "Urgent")
                    Tag(label = "Home")
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "menu"
                )
            }
        }
    }
}