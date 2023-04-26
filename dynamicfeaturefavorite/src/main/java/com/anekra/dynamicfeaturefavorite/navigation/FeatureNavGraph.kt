package com.anekra.dynamicfeaturefavorite.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.anekra.dynamicfeaturefavorite.favoritedetails.navigation.favoriteDetailsRoute

@Composable
fun FeatureNavGraph(
    startDestination: String,
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        startDestination = startDestination,
        navController = navHostController
    ) {
        favoriteRoute(navHostController = navHostController)
        favoriteDetailsRoute(navHostController = navHostController)
    }
}