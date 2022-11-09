package com.example.lykkehjul.data

data class Category(
    val name: String,
    val words: Array<String>,
)

val words = listOf(
    Category(
        "Disney film",
        arrayOf(
            "Shrek",
            "Syng",
            "Minions",
            "Den lille havfrue",
            "Toy Story"
        )
    ),
    Category(
        "Byer",
        arrayOf(
            "København",
            "Næstved",
            "Odense",
            "Sønderborg",
            "Skive"
        )
    )
)