package com.anekra.capstoneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.anekra.capstoneapp.presentation.screen.MainScreen
import com.anekra.capstoneapp.ui.theme.AnekrasCapstoneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private var splashOpen = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition{
            splashOpen
        }
        super.onCreate(savedInstanceState)
        setContent {
            AnekrasCapstoneTheme {
                MainScreen(onScreenLoaded = { splashOpen = false })
            }
        }
    }
}