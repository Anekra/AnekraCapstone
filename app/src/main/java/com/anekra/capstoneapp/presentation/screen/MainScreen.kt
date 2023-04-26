package com.anekra.capstoneapp.presentation.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anekra.capstoneapp.navigation.MainNavGraph
import com.anekra.home.component.topbar.HomeTopBar
import com.anekra.search.component.topbar.SearchTopBar
import com.anekra.ui.component.bottomnav.BottomNav
import com.anekra.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navHostController: NavHostController = rememberNavController(),
    onScreenLoaded: () -> Unit,
    startDestination: String
) {
    val currentDestination = navHostController.currentBackStackEntryAsState().value?.destination
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val context = LocalContext.current
    
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            when (currentDestination?.route) {
                Screens.Home.route -> HomeTopBar(scrollBehavior = scrollBehavior)
                Screens.Search.route -> SearchTopBar(scrollBehavior = scrollBehavior)
            }
        },
        bottomBar = {
            currentDestination?.let {
                BottomNav(
                    navHostController = navHostController,
                    currentDestination = it,
                    context = context
                )
            }
        }
    ) { paddingValues ->
        MainNavGraph(
            startDestination = startDestination,
            navHostController = navHostController,
            paddingValues = paddingValues,
            onScreenLoaded = onScreenLoaded
        )
    }
}