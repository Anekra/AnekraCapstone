package com.anekra.dynamicfeaturefavorite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.anekra.dynamicfeaturefavorite.navigation.FeatureNavGraph
import com.anekra.ui.theme.AnekrasCapstoneTheme
import com.anekra.util.Screens

class DynamicFeatureActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            AnekrasCapstoneTheme {
                FeatureNavGraph(startDestination = Screens.Favorite.route)
            }
        }
    }
}