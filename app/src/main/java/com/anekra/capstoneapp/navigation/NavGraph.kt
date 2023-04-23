package com.anekra.capstoneapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.anekra.capstoneapp.navigation.screen.details.detailsRoute
import com.anekra.capstoneapp.navigation.screen.favorite.favoriteRoute
import com.anekra.capstoneapp.navigation.screen.home.homeRoute
import com.anekra.capstoneapp.navigation.screen.search.searchRoute

@Composable
fun MainNavGraph(
    startDestination: String,
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    onScreenLoaded: () -> Unit,
) {
    NavHost(
        startDestination = startDestination,
        navController = navHostController
    ) {
        homeRoute(
            navHostController = navHostController,
            paddingValues = paddingValues,
            onScreenLoaded = onScreenLoaded
        )
        searchRoute(
            navHostController = navHostController,
            paddingValues = paddingValues
        )
        favoriteRoute(navHostController = navHostController, paddingValues = paddingValues)
        detailsRoute(navHostController = navHostController)
    }
}