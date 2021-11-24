package com.senla.notebooksenla

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.senla.notebooksenla.MainActivity.Companion.ITEM_CLICKED_EXTRA
import com.senla.notebooksenla.RecyclerViewAdapter.Companion.ITEM_CLICKED_DEFAULT_VALUE
import com.senla.notebooksenla.databinding.ActivityNoteBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    companion object {
        private const val TIME_PATTERN = "dd/M/yyyy"
        val listOfFilesData: MutableList<Pair<String, String>> = mutableListOf()
    }

    private lateinit var binding: ActivityNoteBinding
    private lateinit var file: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteFile = NoteFile(this)
        if (intent.getIntExtra(ITEM_CLICKED_EXTRA, ITEM_CLICKED_DEFAULT_VALUE)
            != ITEM_CLICKED_DEFAULT_VALUE) {
            file = noteFile.generateFile(listOfFilesData[intent
                .getIntExtra(ITEM_CLICKED_EXTRA, ITEM_CLICKED_DEFAULT_VALUE)].first)
            binding.noteInputEditText.setText(file.readText())
        }

        binding.saveNoteButton.setOnClickListener {
            val noteName = binding.noteInputEditText.text.lines()[0].trim()
            val currentDate = SimpleDateFormat(TIME_PATTERN).format((Date()))

            file = noteFile.generateFile(noteName)
            file.writeText(binding.noteInputEditText.text.toString())

            if (intent.getIntExtra(ITEM_CLICKED_EXTRA, ITEM_CLICKED_DEFAULT_VALUE)
                != ITEM_CLICKED_DEFAULT_VALUE) {
                listOfFilesData[intent.getIntExtra(ITEM_CLICKED_EXTRA,
                    ITEM_CLICKED_DEFAULT_VALUE)] = Pair(noteName, currentDate)
            } else {
                listOfFilesData.add(Pair(noteName, currentDate))
            }
        }
    }
}