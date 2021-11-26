package com.senla.notebooksenla

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.senla.notebooksenla.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object {
        const val DOCUMENTS = "documents"
        const val ITEM_POSITION_EXTRA = "ITEM_POSITION_EXTRA"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var filesAdapter: RecyclerViewAdapter
    private lateinit var documentsDirectory: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        documentsDirectory = File(filesDir, DOCUMENTS)

        if (documentsDirectory.exists().not()) {
            documentsDirectory.mkdir()
        }

        filesAdapter = RecyclerViewAdapter { position ->
            startActivity(Intent(this@MainActivity, NoteActivity::class.java)
                .apply { putExtra(ITEM_POSITION_EXTRA, position) })
        }
        binding.recyclerView.adapter = filesAdapter
        binding.createNoteButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, NoteActivity::class.java))
        }
    }

    override fun onStart() {
        filesAdapter.submitList((File(filesDir, DOCUMENTS).listFiles()?.toList()))

        super.onStart()
    }
}