package com.senla.notebooksenla

import android.content.Context
import android.widget.EditText
import java.io.File

class NoteFile(private val context: Context) {

    companion object {
        const val FILE_TXT = ".txt"
    }

    private val file: File = File(context.filesDir, MainActivity.DOCUMENTS)

    fun generateFile(noteName: String): File {
        return File("${context.filesDir}${File.separator}" +
                    "${MainActivity.DOCUMENTS}${File.separator}" + noteName
                    + FILE_TXT)
    }

    fun setFile(position: Int): String? {
            return file.listFiles()?.filterIndexed { index, _ -> index == position }
                ?.get(0)?.readText()
    }

    fun editFile(position: Int, text: String) {
        file.listFiles()
            ?.filterIndexed { index, _ -> index == position }?.get(0)
            ?.writeText(text)
    }
}