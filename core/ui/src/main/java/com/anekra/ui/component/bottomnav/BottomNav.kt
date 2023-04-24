package com.anekra.ui.component.bottomnav

import android.content.Context
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Composable
fun BottomNav(
    navHostController: NavHostController,
    currentDestination: NavDestination,
    context: Context,
) {
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Favorite
    )
    val bottomBarDestination = bottomNavItems.any { it.route == currentDestination.route }
    
    if (bottomBarDestination) {
        NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
            bottomNavItems.forEach {
                AddNavItem(
                    bottomNavItem = it,
                    currentDestination = currentDestination,
                    navController = navHostController,
                    context = context
                )
            }
        }
    }
}

@Composable
fun RowScope.AddNavItem(
    bottomNavItem: BottomNavItem,
    currentDestination: NavDestination,
    navController: NavHostController,
    context: Context,
) {
    NavigationBarItem(
        selected = currentDestination.hierarchy.any {
            it.route == bottomNavItem.route
        },
        label = {
            Text(text = bottomNavItem.getTitle(context = context))
        },
        icon = {
            Icon(
                imageVector = bottomNavItem.icon,
                contentDescription = "Navigation Icon"
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.surface,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        onClick = {
            navController.navigate(bottomNavItem.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}