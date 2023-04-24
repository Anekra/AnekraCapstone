package com.anekra.ui.component.bottomnav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anekra.ui.R
import com.anekra.util.Esrb

@Composable
fun EsrbRatingHolder(
    modifier: Modifier,
    esrbRating: String,
) {
    Box(
        modifier = modifier
            .width(40.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        val esrbId = Esrb.values().find { it.rating == esrbRating }?.image
        Image(
            painter = painterResource(id = esrbId ?: R.drawable.esrb_pending),
            contentDescription = "Esrb Rating Image"
        )
    }
}