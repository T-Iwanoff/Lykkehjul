package com.example.lykkehjul.model

data class Letter(
    val char: Char,
    var hidden: Boolean = true
)

class WordHandler(word: String) {
    private var wordArray: Array<Letter>

    init {
        wordArray = Array(word.length) {
                i -> Letter(word[i])
        }
    }

    fun newWord(word: String) {
        wordArray = Array(word.length) {
                i -> Letter(word[i])
        }
    }

    fun getWord(): Array<Letter> {
        return wordArray
    }

    fun revealLetter(letterPos: Int) {
        wordArray[letterPos].hidden = false
    }

    fun revealLetter(char: Char): Int {
        var count = 0
        for (letter in wordArray) {
            if (letter.char == char) {
                count++
                letter.hidden = false
            }
        }
        return count
    }

    fun countLetter(char: Char): Int {
        var count = 0
        for (letter in wordArray) {
            if (letter.char == char) {
                count++
            }
        }
        return count
    }
}