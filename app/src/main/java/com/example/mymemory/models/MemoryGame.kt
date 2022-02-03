package com.example.mymemory.models

import com.example.mymemory.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize: BoardSize) {

    val cards: List<MemoryCard>
    var numPairsFound = 0
    private var indexOfSingleSelectedInt: Int? = null

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizeImages = (chosenImages + chosenImages).shuffled()
        cards = randomizeImages.map { MemoryCard(it) }
    }

    fun flipCard(position: Int) : Boolean {
        val card = cards[position]
        var foundMatch = false
        // Three cases
        // Zero cards previously flipped over -> flip over selected card (note, you can here simply also restore cards) => same as last case
        // One card previously flipped over -> flip over selected card + check if images match
        // Two cards previously flipped over -> restore cards + flip over the selected card
        if (indexOfSingleSelectedInt == null) {
            // 0 or 2 cards previously flipped over
                restoreCards()
            indexOfSingleSelectedInt = position
        } else {
            // Exactly 1 card previously flippped over
            foundMatch = checkForMatch(indexOfSingleSelectedInt!!, position)
            indexOfSingleSelectedInt = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int) : Boolean {
        if (cards[position1].identifier != cards[position2].identifier) {
            return false
        } else {
            cards[position1].isMatched = true
            cards[position2].isMatched = true
            numPairsFound++
            return true
        }
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }
}