package com.anekra.capstoneapp.presentation.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anekra.capstoneapp.R

@Composable
fun DisclaimerContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.9f)
                .clip(RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.padding(bottom = 8.dp),
                imageVector = Icons.Default.Warning,
                contentDescription = "Warning Icon",
                colorFilter = ColorFilter.tint(color = Color.Yellow)
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = "\"I do not own the contents within this app, this app is just for demonstration purposes, to show cast my skills as a mobile developer.\"",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = "All the credits goes to Rawg.io for providing the api service.",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            )
            Image(
                modifier = Modifier.clip(RoundedCornerShape(5.dp)),
                painter = painterResource(id = R.drawable.rawg_logo),
                contentDescription = "Rawg Logo"
            )
        }
    }
}