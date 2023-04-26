package com.anekra.capstoneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.anekra.capstoneapp.presentation.screen.MainScreen
import com.anekra.ui.theme.AnekrasCapstoneTheme
import com.anekra.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private var splashOpen = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            splashOpen
        }
        super.onCreate(savedInstanceState)
        setContent {
            AnekrasCapstoneTheme {
                var route by remember { mutableStateOf(Screens.Home.route) }
                
                LaunchedEffect(key1 = intent) {
                    route = intent.getStringExtra("route") ?: Screens.Home.route
                }
                MainScreen(
                    onScreenLoaded = { splashOpen = false },
                    startDestination = route
                )
            }
        }
    }
}