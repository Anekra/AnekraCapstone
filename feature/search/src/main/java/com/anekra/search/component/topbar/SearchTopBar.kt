package com.anekra.search.component.topbar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        title = {
            Text(text = "Explore")
        },
        scrollBehavior = scrollBehavior
    )
}