package com.clozanodev.matriculas.game

object GameLogic {

    fun calculateScore(licensePlate: String, word: String): Int {

        val letterScore = mapOf(
            'A' to 1, 'B' to 4, 'C' to 2, 'D' to 2, 'E' to 1, 'F' to 6, 'G' to 4,
            'H' to 4, 'I' to 1, 'J' to 8, 'K' to 10, 'L' to 2, 'M' to 3, 'N' to 1,
            'Ñ' to 10, 'O' to 1, 'P' to 3, 'Q' to 6, 'R' to 1, 'S' to 1, 'T' to 2,
            'U' to 3, 'V' to 6, 'W' to 10, 'X' to 10, 'Y' to 6, 'Z' to 8
        )

        val letters = licensePlate.substring(4).uppercase()
        val numbers = licensePlate.substring(0, 4).toList().map { it.toString().toInt() }
        val upperWord = word.uppercase()

        var score = upperWord.sumOf { letterScore[it] ?: 0 }

        if (upperWord.contains(letters[0]) && upperWord.contains(letters[1]) && upperWord.contains(letters[2])) {
            score *= 2
        }

        if (checkOrder(letters, upperWord)){
            score *= 3
        }

        numbers.forEach { number ->
            if (upperWord.length == number){
                score *= 2
            }
        }
        return score
    }

    fun getMedal(score: Int): String {
        return when {
            score >= 450 -> "Gold"
            score >= 300 -> "Silver"
            score >= 150 -> "Bronze"
            else -> "No medal"
        }
    }
}

fun checkOrder(letters: String, word: String): Boolean {

    val firstLetter = letters[0]
    val secondLetter = letters[1]
    val thirdLetter = letters[2]

    val firstIndices = word.indices.filter { word[it] == firstLetter }
    val secondIndices = word.indices.filter { word[it] == secondLetter }
    val thirdIndices = word.indices.filter { word[it] == thirdLetter }

    for (i in firstIndices) {
        for (j in secondIndices) {
            for (k in thirdIndices) {
                if (i < j && j < k) {
                    return true
                }
            }
        }
    }
    return false
}

