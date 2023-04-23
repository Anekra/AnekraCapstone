package com.anekra.capstoneapp.presentation.component.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anekra.capstoneapp.util.CategoriesIcons
import com.anekra.capstoneapp.util.showToast

@Composable
fun CategoriesContent(
    context: Context
) {
    Row(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        LazyVerticalGrid(
            modifier = Modifier.height(200.dp),
            columns = GridCells.Fixed(count = 4),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(count = 8) {
                Column(
                    modifier = Modifier.clickable {
                        showToast(
                            message = CategoriesIcons.values()[it].name,
                            context = context
                        )
                    }
                ) {
                    CategoryIcon(iconId = CategoriesIcons.values()[it].icon)
                    CategoryText(category = CategoriesIcons.values()[it].name)
                }
            }
        }
    }
}

@Composable
fun CategoryIcon(
    iconId: Int,
) {
    Box(
        modifier = Modifier.padding(4.dp)
    ) {
        Image(painter = painterResource(id = iconId), contentDescription = "Categories Icons")
    }
}

@Composable
fun CategoryText(
    category: String,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = category,
        style = TextStyle(
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            color = MaterialTheme.colorScheme.onSurface,
        ),
        textAlign = TextAlign.Center
    )
}