package com.senla.notebooksenla

import android.content.Context
import java.io.File

class NoteFile(private val context: Context) {

    companion object {
        const val FILE_TXT = ".txt"
    }

    fun generateFile(noteName: String): File {
        return File("${context.filesDir}${File.separator}" +
                    "${MainActivity.DOCUMENTS}${File.separator}" + noteName
                    + FILE_TXT)
    }
}