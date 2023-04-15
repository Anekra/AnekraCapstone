package com.anekra.capstoneapp.util

import androidx.compose.ui.graphics.Color

enum class Genres(
    val contentColor: Color,
    val textColor: Color
) {
    Action(
        contentColor = Color(0xFF004f4f),
        textColor = Color(0xFF6ff7f6)
    ),
    Indie(
        contentColor = Color(0xFF822800),
        textColor = Color(0xFFffdbcf)
    ),
    Adventure(
        contentColor = Color(0xFF3239a3),
        textColor = Color(0xFFe0e0ff)
    ),
    Rpg(
        contentColor = Color(0xFF554500),
        textColor = Color(0xFFffe179)
    ),
    Strategy(
        contentColor = Color(0xFF494900),
        textColor = Color(0xFFe9e86b)
    ),
    Shooter(
        contentColor = Color.LightGray,
        textColor = Color(0xFF3f484a)
    )
}