package com.anekra.capstoneapp.presentation.component.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anekra.capstoneapp.R
import com.anekra.capstoneapp.domain.model.game.GameDetails
import com.anekra.capstoneapp.util.Esrb

@Composable
fun DetailsAdditionalInfoContent(game: GameDetails) {
    Row(modifier = Modifier.padding(start = 16.dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "ESRB Rating",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.outline,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                val esrb = Esrb.values().find {
                    it.name == game.esrbRating?.name.toString()
                }
                Image(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    painter = painterResource(id = esrb?.image ?: R.drawable.esrb_pending),
                    contentDescription = "Esrb Rating Image"
                )
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = esrb?.description ?: "",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}