package com.birajsilwal.mymemory.models

/*data class to represent one memory card*/
data class MemoryCard(
        // value of val can not be changes once set
        val identifier : Int,
        val imageUrl: String? = null,
        // value of var can be changed
        var isFaceUp : Boolean = false,
        var isMatched : Boolean = false
)