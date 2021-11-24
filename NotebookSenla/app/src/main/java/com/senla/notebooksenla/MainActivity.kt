package com.senla.notebooksenla

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.senla.notebooksenla.NoteActivity.Companion.listOfFilesData
import com.senla.notebooksenla.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    companion object {
        const val DOCUMENTS = "documents"
        const val ITEM_CLICKED_EXTRA = "ITEM_CLICKED_EXTRA"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        File(filesDir, DOCUMENTS).mkdir()
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.createNoteButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, NoteActivity::class.java))
        }
    }

    override fun onResume() {
        binding.recyclerView.adapter = RecyclerViewAdapter(listOfFilesData,
            this@MainActivity)

        super.onResume()
    }

    override fun onItemClick(position: Int) {
        startActivity(Intent(this, NoteActivity::class.java).apply {
            putExtra(ITEM_CLICKED_EXTRA, position)
        })
    }
}