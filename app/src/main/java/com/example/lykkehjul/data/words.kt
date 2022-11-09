package com.example.lykkehjul.data

data class Category(
    val name: String,
    val words: Array<String>,
)

val words = listOf(
    Category(
        "Disney film",
        arrayOf(
            "SHREK",
            "MINIONS",
            "DEN LILLE HAVFRUE",
            "TOY STORY",
        )
    ),
    /*Category(
        "Byer",
        arrayOf(
            "KØBENHAVN",
            "NÆSTVED",
            "ODENSE",
            "SØNDERBORG",
            "SKIVE"
        )
    )*/
)