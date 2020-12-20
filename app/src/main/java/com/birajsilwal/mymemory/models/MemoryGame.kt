package com.birajsilwal.mymemory.models

import com.birajsilwal.mymemory.utils.DEFAULT_ICONS

/*this class encapsulates logic for the memory game,
* takes board size and creates images
* according to the board size*/
class MemoryGame (private val boardSize : BoardSize ) {

    val cards : List<MemoryCard>
    var numPairsFound = 0

    private var indexOfSingleSelectedCard : Int? = null

    init {
        // shuffle the list and get the number of images
        // according to the size of the game
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        //randomize the chosed images and double that up
        val randomizedImages = (chosenImages + chosenImages)
        cards = randomizedImages.map { MemoryCard(it) }
    }


    fun flipCard(position: Int) : Boolean {
        val card = cards[position]
        // three cases for fliping
        // 0 cards previously flipped over => flip over the selected
        // 1 cards previously flipped over => flip over the selected + check if they matches
        // 2 cards previously flipped over => restore cards + flip over the selected card
        var foundMatch = false
        if (indexOfSingleSelectedCard == null) {
            // 0 or 2 cards previously flipped over
            restoreCards()
            indexOfSingleSelectedCard = position
        }
        else {
            //exactly 1 card previously flipped over
            val foundMath = checkForMatch(indexOfSingleSelectedCard !!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int) : Boolean {
        if (cards[position1].identifier != cards[position2].identifier) {
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    private fun restoreCards() {
        for( card in cards) {
            if(!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }
}