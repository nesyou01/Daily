package com.nesyou.daily.features.home.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nesyou.daily.R
import com.nesyou.daily.core.domain.utils.Helpers.capitalizeWord
import com.nesyou.daily.core.ui.components.TaskCard
import com.nesyou.daily.features.home.ui.components.StaggeredItem
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    val user = Firebase.auth.currentUser!!
    CompositionLocalProvider(
        LocalOverScrollConfiguration provides null
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(id = R.dimen.horizontal_padding),
                    vertical = 15.dp
                ),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        stringResource(
                            R.string.hi_with_comma,
                            user.displayName!!.let {
                                it.substring(0, it.indexOf(" "))
                            }.capitalizeWord(),
                        ),
                        style = MaterialTheme.typography.h2.copy(fontSize = 27.sp),
                        color = MaterialTheme.colors.primaryVariant
                    )
                    Text(
                        stringResource(R.string.let_is_make_this_day),
                        style = MaterialTheme.typography.h6
                    )
                }
                Surface(
                    elevation = 5.dp,
                    shape = MaterialTheme.shapes.medium,
                ) {
                    GlideImage(
                        imageModel = user.photoUrl ?: R.drawable.profile,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    stringResource(R.string.my_tasks),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.primaryVariant
                )
                TasksGrid()
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(R.string.today_tasks),
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.primaryVariant
                    )
                    TextButton(onClick = { /*TODO*/ }) {
                        Text("View all")
                    }
                }
                Column {
                    repeat(6) {
                        TaskCard(
                            modifier = Modifier.padding(bottom = 10.dp),
                            onClick = {}
                        )
                    }
                }
            }
        }
    }

}


@Composable
private fun TasksGrid() {
    Row(
        modifier = Modifier
            .height(380.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5F),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            StaggeredItem(
                weight = 0.6F,
                colors = listOf(
                    Color(0xFF7DC8E7).copy(alpha = 0.69F),
                    Color(0xFF7DC8E7),
                ),
                icon = R.drawable.imac,
                title = "Completed",
                length = 15,
                mainColor = MaterialTheme.colors.primaryVariant
            )
            StaggeredItem(
                weight = 0.45F,
                colors = listOf(
                    Color(0xFFE77D7D).copy(alpha = 0.71F),
                    Color(0xFFE77D7D),
                ),
                icon = R.drawable.ic_close_square,
                title = "Completed",
                length = 15
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5F),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            StaggeredItem(
                weight = 0.5F,
                colors = listOf(
                    Color(0xFF7D88E7).copy(alpha = .74F),
                    Color(0xFF7D88E7),
                ),
                icon = R.drawable.ic_time_square,
                title = "Completed",
                length = 15
            )
            StaggeredItem(
                weight = 0.65F,
                colors = listOf(
                    Color(0xFF81E89E).copy(alpha = .35F),
                    Color(0xFF81E89E),
                ),
                icon = R.drawable.folder,
                title = "Completed",
                length = 15,
                mainColor = MaterialTheme.colors.primaryVariant
            )
        }
    }
}

