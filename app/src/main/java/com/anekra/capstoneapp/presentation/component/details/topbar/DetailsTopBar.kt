package com.anekra.capstoneapp.presentation.component.details.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Game Details")
        },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow Icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}