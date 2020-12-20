package com.birajsilwal.mymemory.models

import com.birajsilwal.mymemory.utils.DEFAULT_ICONS

/*this class encapsulates logic for the memory game,
* takes board size and creates images
* according to the board size*/
class MemoryGame (private val boardSize : BoardSize ) {

    val cards : List<MemoryCard>
    val numPairsFound = 0

    init {
        // shuffle the list and get the number of images
        // according to the size of the game
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())

        //randomize the chosed images and double that up
        val randomizedImages = (chosenImages + chosenImages)

        cards = randomizedImages.map { MemoryCard(it) }
    }
}