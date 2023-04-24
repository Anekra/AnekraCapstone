package com.anekra.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anekra.domain.model.game.GameList

@Composable
fun FeaturedFooter(data: GameList?) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = data?.name.toString(),
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = data?.released?.substring(0, 4) ?: "Coming soon",
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}