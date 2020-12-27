package com.birajsilwal.mymemory

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.birajsilwal.mymemory.models.BoardSize
import com.birajsilwal.mymemory.models.MemoryCard
import com.birajsilwal.mymemory.models.MemoryGame
import com.birajsilwal.mymemory.utils.DEFAULT_ICONS
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    // lateinit -> late initialization
    private lateinit var clRoot: ConstraintLayout
    private lateinit var rvBoard: RecyclerView
    private lateinit var tvNumMoves: TextView
    private lateinit var tvNumPairs: TextView
    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: MemoryBoardAdapter
    private var boardSize : BoardSize = BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clRoot = findViewById(R.id.clRoot)
        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.tvNumMoves)
        tvNumPairs = findViewById(R.id.tvNumPairs)

        // initial color is red, it will turn to color full (green) as user progress the game
        tvNumPairs.setTextColor(ContextCompat.getColor(this, R.color.color_progress_none))
        memoryGame = MemoryGame(boardSize)
        adapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards,
                object : MemoryBoardAdapter.CardClickListener {
                    override fun onCardClicked(position: Int) {
                        updateGameWithFlip(position)
                    }
                })
        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }

    /**@param position as int
     *this method is responsible for
     * updating memory game with an attempt
     * flip at given position*/
    private fun updateGameWithFlip(position: Int) {
        // Error checking
        if (memoryGame.haveWonGame()) {
            // Alert the user of an invalid move
            Snackbar.make(clRoot, "You already won!", Snackbar.LENGTH_LONG).show()
            return
        }
        if (memoryGame.isCardFaceUp(position)) {
            // Alert the user of an invalid move
            Snackbar.make(clRoot, "Invalid move", Snackbar.LENGTH_SHORT).show()
            return
        }
        // Actually flip over the card
        if (memoryGame.flipCard(position)) {
            Log.i(TAG, "Found a match! Num pairs found: ${memoryGame.numPairsFound}")
            // liner interpolation of the color
            // for instance, if someone is walking for a 1000 steps, and is 75%
            // done, then we estimate that someone walked 750 steps so far
            val color = ArgbEvaluator().evaluate(
                    memoryGame.numPairsFound.toFloat() / boardSize.getNumPairs(),
                    ContextCompat.getColor(this, R.color.color_progress_none),
                    ContextCompat.getColor(this, R.color.color_progress_full)
            ) as Int
            tvNumPairs.setTextColor(color)
            tvNumPairs.text = "Pairs: ${memoryGame.numPairsFound} / ${boardSize.getNumPairs()}"
            if (memoryGame.haveWonGame()) {
                Snackbar.make(clRoot, "You won! Congratulations", Snackbar.LENGTH_LONG).show()
            }
        }
        tvNumMoves.text = "Moves: ${memoryGame.getNumMoves()}"
        adapter.notifyDataSetChanged()
    }
}