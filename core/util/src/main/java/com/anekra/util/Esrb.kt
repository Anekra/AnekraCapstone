package com.anekra.util

enum class Esrb(
    val rating: String,
    val image: Int,
    val description: String
) {
    Everyone(
        rating = "Everyone",
        image = R.drawable.esrb_everyone,
        description = "Content is generally suitable for all ages. May contains minimal cartoon, fantasy of mild violence and/or infrequent use of mild language."
    ),
    Everyone10Plus(
        rating = "Everyone 10+",
        image = R.drawable.esrb_everyone10,
        description = "Content is generally suitable for ages 10 and up. May contain more cartoon, fantasy or mild violence, mild language and/or minimal suggestive themes."
    ),
    Teen(
        rating = "Teen",
        image = R.drawable.esrb_teen,
        description = "Content is generally suitable for ages 13 and up. May contain violence, suggestive themes, crude humor, minimal blood, simulated gambling and/or infrequent use of strong language."
    ),
    Mature(
        rating = "Mature 17+",
        image = R.drawable.esrb_mature,
        description = "Content is generally suitable for ages 17 and up. May contain intense violence, blood and gore, sexual content and/or strong language."
    ),
    Adult(
        rating = "Adults Only 18+",
        image = R.drawable.esrb_adult,
        description = "Content suitable only for adults ages 18 and up. May include prolonged scenes of intense violence, graphic sexual content and/or gambling with real currency."
    ),
    Pending(
        rating = "Rating Pending",
        image = R.drawable.esrb_pending,
        description = "Not yet assigned a final ESRB rating. Appears only in advertising, marketing and promotional materials related to a physical (e.g., boxed) video game that is expected to carry an ESRB rating, and should be replaced by a game's rating once it has been assigned."
    ),
}