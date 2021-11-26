package com.senla.notebooksenla

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.senla.notebooksenla.MainActivity.Companion.ITEM_POSITION_EXTRA
import com.senla.notebooksenla.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    companion object {
        const val ITEM_POSITION_DEFAULT_VALUE = -1
    }

    private lateinit var binding: ActivityNoteBinding
    private var position = ITEM_POSITION_DEFAULT_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        position = intent.getIntExtra(ITEM_POSITION_EXTRA, ITEM_POSITION_DEFAULT_VALUE)
        val noteFile = NoteFile(this)
        binding.apply {

            if (position != ITEM_POSITION_DEFAULT_VALUE) {
                noteInputEditText.setText(noteFile.getFileContentByPosition(position = position))
            }

            binding.saveNoteButton.setOnClickListener {
                if (position == ITEM_POSITION_DEFAULT_VALUE) {
                    noteFile.generateFile(noteName = noteInputEditText.text.lines()[0].trim())
                        .writeText(noteInputEditText.text.toString())
                } else {
                    noteFile.editFile(position = position, noteInputEditText.text.toString())
                }
            }
        }
    }
}