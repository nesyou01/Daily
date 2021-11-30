package com.nesyou.daily.features.auth.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesyou.daily.R
import com.nesyou.daily.core.ui.components.ExpandedButton

@Composable
fun SocialWithButton(
    onClick: () -> Unit,
    buttonText: String
) {
    Column(
        modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.medium)),
        verticalArrangement = Arrangement.spacedBy(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExpandedButton(
            text = buttonText,
            onClick = onClick,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.weight(1F)
            )
            Text(
                stringResource(R.string.or_with), style = TextStyle(
                    fontSize = 13.sp,
                    color = MaterialTheme.colors.secondaryVariant
                ),
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.medium))
            )
            Divider(
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.weight(1F)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            OutlinedIconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "google"
                )
            }
            OutlinedIconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "facebook"
                )
            }
        }
    }
}