package com.birajsilwal.mymemory

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.birajsilwal.mymemory.models.BoardSize
import com.birajsilwal.mymemory.utils.EXTRA_BOARD_SIZE

/*This is where user can create there own board*/
class CreateActivity : AppCompatActivity() {

    // late init -> declare them as member variable and
    // set the value on create
    private lateinit var rvImagePicker: RecyclerView
    private lateinit var etGameName: EditText
    private lateinit var btnSave: Button

    private lateinit var boardSize: BoardSize
    private var numImagesRequired  = -1
    private val chosenImageUris = mutableListOf<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        rvImagePicker = findViewById(R.id.rvImagePicker)
        etGameName = findViewById(R.id.etGameName)
        btnSave = findViewById(R.id.btnSave)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        boardSize = intent.getSerializableExtra(EXTRA_BOARD_SIZE) as BoardSize
        numImagesRequired = boardSize.getNumPairs()
        supportActionBar?.title = "Choose pics (0 / $numImagesRequired)"

        rvImagePicker.adapter = ImagePickerAdapter(this, chosenImageUris, boardSize)
        rvImagePicker.setHasFixedSize(true)
        // Recycler view have two core components
        // adapter and layout manager
        rvImagePicker.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            //done with this activity and go back to main activity
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}