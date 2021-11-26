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
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var documentsDirectory: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        documentsDirectory = File(filesDir, DOCUMENTS)

        if (documentsDirectory.exists().not()) {
            documentsDirectory.mkdir()
        }

        binding.createNoteButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, NoteActivity::class.java))
        }
        setAdapter(listOfFiles = documentsDirectory)
    }

    override fun onResume() {
        adapter.submitList(documentsDirectory.listFiles()?.toMutableList())

        super.onResume()
    }

    private fun setAdapter(listOfFiles: File) {
        binding.recyclerView.adapter = RecyclerViewAdapter { position ->
            startActivity(Intent(this@MainActivity, NoteActivity::class.java)
                .apply { putExtra(ITEM_POSITION_EXTRA, position) })
        }
        adapter = binding.recyclerView.adapter as RecyclerViewAdapter
        adapter.submitList(listOfFiles.listFiles()?.toMutableList())
    }
}