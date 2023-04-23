package com.anekra.capstoneapp.navigation.screen

import com.anekra.capstoneapp.util.Constants.DETAILS_SCREEN
import com.anekra.capstoneapp.util.Constants.FAVORITE_SCREEN
import com.anekra.capstoneapp.util.Constants.GAME_ID_ARGS
import com.anekra.capstoneapp.util.Constants.HOME_SCREEN
import com.anekra.capstoneapp.util.Constants.SEARCH_SCREEN

sealed class Screens(val route: String) {
    object Home: Screens(route = HOME_SCREEN)
    object Search: Screens(route = SEARCH_SCREEN)
    object Favorite: Screens(route = FAVORITE_SCREEN)
    object Details: Screens(route = "$DETAILS_SCREEN?$GAME_ID_ARGS={$GAME_ID_ARGS}") {
        fun passGameId(gameId: String) = "$DETAILS_SCREEN?$GAME_ID_ARGS=$gameId"
    }
}