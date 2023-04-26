package com.anekra.ui.component.bottomnav

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.anekra.util.Screens
import com.anekra.util.showToast
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

@Composable
fun BottomNav(
    navHostController: NavHostController,
    currentDestination: NavDestination,
    context: Context
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
    context: Context
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
            when (val route = bottomNavItem.route) {
                Screens.Home.route -> {
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                    
                }
                Screens.Search.route -> {
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
                Screens.Favorite.route -> {
                    val manager = SplitInstallManagerFactory.create(context)
                    val module = "dynamicfeaturefavorite"
                    if (manager.installedModules.contains(module)) {
                        val intent = Intent().setClassName(
                            context.packageName,
                            "com.anekra.dynamicfeaturefavorite.DynamicFeatureActivity"
                        )
                        ContextCompat.startActivity(context, intent, null)
                    } else {
                        val request = SplitInstallRequest.newBuilder()
                            .addModule(module)
                            .build()
                        manager.startInstall(request).addOnSuccessListener {
                        }.addOnFailureListener {
                            showToast("Feature not installed", context)
                        }
                    }
                    
                }
            }
            
        }
    )
}