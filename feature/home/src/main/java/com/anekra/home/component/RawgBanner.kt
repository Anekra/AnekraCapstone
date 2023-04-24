package com.anekra.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anekra.home.R

@Composable
fun RawgContent() {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            painter = painterResource(id = R.drawable.rawg_one),
            contentDescription = "Rawg Image"
        )
    }
}