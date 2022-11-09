package com.example.lykkehjul.model

import com.example.lykkehjul.data.words

class WordSelector() {
    private var category: Int = 0
    private var word: String = ""

    init {
        findCategory()
        findWord()
    }

    private fun findCategory() {
        val categoryCount: Int = words.size-1
        category = (0..categoryCount).random()
    }

    private fun findWord() {
        val wordCount: Int = words[category].words.size-1
        val chosen: Int = (0..wordCount).random()
        word = words[category].words[chosen]
    }

    fun newWord() {
        findCategory()
        findWord()
    }

    fun getWordArray(): Array<Char> {
        val chars: Array<Char> = Array(word.length) {
            i -> word.get(i)
        }
        return chars
    }

    fun getCategory(): String {
        return words[category].name
    }

    fun getWord(): String {
        return word
    }
}