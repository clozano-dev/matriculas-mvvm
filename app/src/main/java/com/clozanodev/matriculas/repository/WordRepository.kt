package com.clozanodev.matriculas.repository

import android.content.Context
import com.clozanodev.matriculas.R

class WordRepository (context: Context){
    private val words: Set<String> = loadWordsFromAssets(context)

    private fun loadWordsFromAssets(context: Context): Set<String> {
        val words = mutableSetOf<String>()
        val resources = context.resources
        val wordsArray = resources.getStringArray(R.array.word_list)
        words.addAll(wordsArray.map { it.uppercase()} )
        return words
    }

    fun isWordValid(word: String): Boolean {
        return words.contains(word.uppercase())
    }
}