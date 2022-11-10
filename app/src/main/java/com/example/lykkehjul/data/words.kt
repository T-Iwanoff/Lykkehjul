package com.example.lykkehjul.data

data class Category(
    val name: String,
    val words: Array<String>,
)

/* Special characters that may appear in a word, such as apostrophe,
   should start out revealed, as the player will not be able to guess
   them. Make sure any such characters are present below
*/
val specialChar = listOf(
    '\'', '-', '?'
)

// A list of words for the player to guess, sorted by category
val words = listOf(
    Category(
        "Disney film",
        arrayOf(
            //"SHREK",
            //"MINIONS",
            "DEN LILLE HAVFRUE",
            //"TOY STORY",
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