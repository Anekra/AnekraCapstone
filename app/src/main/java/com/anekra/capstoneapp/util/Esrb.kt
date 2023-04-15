package com.anekra.capstoneapp.util

import com.anekra.capstoneapp.R

enum class Esrb(
    val rating: String,
    val image: Int
) {
    Everyone(
        rating = "Everyone",
        image = R.drawable.esrb_everyone
    ),
    Everyone10Plus(
        rating = "Everyone 10+",
        image = R.drawable.esrb_everyone10
    ),
    Teen(
        rating = "Teen",
        image = R.drawable.esrb_teen
    ),
    Mature(
        rating = "Mature",
        image = R.drawable.esrb_mature
    ),
    Adult(
        rating = "Adults Only",
        image = R.drawable.esrb_adult
    ),
    Pending(
        rating = "Rating Pending",
        image = R.drawable.esrb_pending
    ),
}