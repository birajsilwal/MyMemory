package com.birajsilwal.mymemory.models

enum class BoardSize(val numCards : Int) {
    EASY(8),
    MEDIUM(18),
    HARD(24);

    /**@return int - width
     * when is like a switch statement*/
    fun getWidth() : Int {
        return when (this) {
            EASY -> 2
            MEDIUM -> 3
            HARD -> 4
        }
    }

    /**@return int - height
     * */
    fun getHeight() : Int {
        return numCards / getWidth()
    }

    /**@return int - number of pairs
     * this method is going to represent how many pairs
     * of cards going to be. For instance, if game is
     * easy mode, then there will be 4 pairs */
    fun getNumPairs() : Int {
        return numCards / 2
    }

}