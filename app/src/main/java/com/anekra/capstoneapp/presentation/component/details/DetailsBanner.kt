package com.anekra.capstoneapp.presentation.component.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anekra.capstoneapp.util.ImageHolder

@Composable
fun DetailsBannerContent(
    image: String
) {
    Row(modifier = Modifier.height(220.dp)) {
        ImageHolder(imageUrl = image, showGradient = false)
    }
}