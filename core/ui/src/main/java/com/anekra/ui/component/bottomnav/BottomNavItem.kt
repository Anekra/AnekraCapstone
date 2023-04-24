package com.anekra.ui.component.bottomnav

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.anekra.ui.R
import com.anekra.util.Screens

sealed class BottomNavItem(
    val titleResId: Int,
    val route: String,
    val icon: ImageVector
) {
    fun getTitle(context: Context): String {
        return context.getString(titleResId)
    }
    object Home : BottomNavItem(
        titleResId = R.string.home,
        route = Screens.Home.route,
        icon = Icons.Default.Home
    )
    object Search : BottomNavItem(
        titleResId = R.string.search,
        route = Screens.Search.route,
        icon = Icons.Default.Search
    )
    object Favorite : BottomNavItem(
        titleResId = R.string.favorite,
        route = Screens.Favorite.route,
        icon = Icons.Default.Favorite
    )
}
