package com.birajsilwal.mymemory

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.birajsilwal.mymemory.models.BoardSize
import com.birajsilwal.mymemory.utils.EXTRA_BOARD_SIZE

class WelcomeActivity : AppCompatActivity() {

    companion object {
        private const val PLAY_NOW = 916
    }

    private lateinit var btnEasy: TextView
    private lateinit var btnMedium: TextView
    private lateinit var btnHard: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btnEasy = findViewById(R.id.btnEasy)
        btnMedium = findViewById(R.id.btnMedium)
        btnHard = findViewById(R.id.btnHard)

        btnEasy.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_BOARD_SIZE, BoardSize.EASY)
            startActivity(intent)
        }

        btnMedium.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_BOARD_SIZE, BoardSize.MEDIUM)
            startActivity(intent)
        }

        btnHard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_BOARD_SIZE, BoardSize.HARD)
            startActivity(intent)
        }
    }
}