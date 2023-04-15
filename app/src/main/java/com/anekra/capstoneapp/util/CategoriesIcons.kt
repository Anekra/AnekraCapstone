package com.anekra.capstoneapp.util

import com.anekra.capstoneapp.R

enum class CategoriesIcons(
    val id: Int,
    val icon: Int
) {
    Creators(
        id = 0,
        icon = R.drawable.ic_creators
    ),
    Developers(
        id = 1,
        icon = R.drawable.ic_developers
    ),
    Games(
        id = 2,
        icon = R.drawable.ic_games
    ),
    Genres(
        id = 3,
        icon = R.drawable.ic_genres
    ),
    Platforms(
        id = 4,
        icon = R.drawable.ic_platforms
    ),
    Publishers(
        id = 5,
        icon = R.drawable.ic_publishers
    ),
    Stores(
        id = 6,
        icon = R.drawable.ic_stores
    ),
    Tags(
        id = 7,
        icon = R.drawable.ic_tags
    )
}