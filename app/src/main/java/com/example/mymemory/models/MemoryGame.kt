package com.example.mymemory.models

import com.example.mymemory.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize: BoardSize) {

    val cards: List<MemoryCard>
    val numPairsFound = 0

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizeImages = (chosenImages + chosenImages).shuffled()
        cards = randomizeImages.map { MemoryCard(it) }
    }

}